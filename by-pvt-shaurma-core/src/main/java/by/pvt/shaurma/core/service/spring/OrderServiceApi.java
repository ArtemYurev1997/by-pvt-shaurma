package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.*;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.exception.PaymentException;
import by.pvt.shaurma.core.mapper.spring.*;
import by.pvt.shaurma.core.repository.spring.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceApi implements OrderApi {
    private final OrderRepository orderRepository;
    private final OrderMappers orderMappers;
    private final ClientRepository clientRepository;
    private final ClientMappers clientMappers;
    private final ShawarmaRepository shawarmaRepository;
    private final ShawarmaMappers shawarmaMappers;
    private final BurgerRepository burgerRepository;
    private final BurgerMappers burgerMappers;
    private final IngridientRepository ingridientRepository;
    private final CommentRepository commentRepository;
    private final CommentMappers commentMappers;

    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {
        return orderMappers.toResponse(orderRepository.save(orderMappers.toEntity(orderRequest)));
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderResponse findById(Long id) {
        return orderMappers.toResponse(orderRepository.findById(id).get());
    }

    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream().map(orderMappers::toResponse).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Client client = clientMappers.toEntity(orderRequest.getUserId());
        Order order = new Order(null, client, 0L, new BigDecimal(0), null, null, null, null, "Не оформлено", "Не оплачено");
        orderRepository.save(order);
        return orderMappers.toResponse(order);
    }

    @Override
    public OrderResponse updateOrderToClient(Long userId, Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Client> client = clientRepository.findById(userId);
        order.get().setUserId(client.get());
        orderRepository.save(order.get());
        return orderMappers.toResponse(order.get());
    }

    @Override
    public OrderResponse checkOut(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Client user = order.get().getUserId();
        order.get().setDate(LocalDate.now());
        order.get().setTelephone(user.getTelephone());
        order.get().setAddress(user.getAddress());
        order.get().setComment(user.getComments().toString());
        order.get().setPayment("Заказ оплачен!");
        order.get().setStatus("Заказ подтвержден!");
        orderRepository.save(order.get());
        return orderMappers.toResponse(order.get());
    }

    @Transactional
    @Override
    public OrderResponse payment(BigDecimal sum, Long orderId, Long userId) {
        BigDecimal cost = orderRepository.getCostOfOrder(orderId, userId);
        int result = sum.compareTo(cost);
        if (result == 0) {
            System.out.println("Оплата прошла успешно! Заказ Оформлен!");
            return checkOut(orderId);
        }
        else {
            throw new PaymentException("Введите нужную сумму!");
        }
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderRepository.getOrdersByUserId(userId).stream().map(orderMappers::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse changeStatus(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().setStatus("Оформлен");
        orderRepository.save(order.get());
        return orderMappers.toResponse(order.get());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderMappers.toResponse(orderRepository.findById(id).get());
    }

    @Override
    public List<ShawarmaDto> getShawarmaDtoByIngridient(String name) {
         return shawarmaRepository.getShawarmasByIngridientName(name).stream().map(shawarmaMappers::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BurgerDto> getBurgersDtoByIngridient(String name) {
        return burgerRepository.getBurgersByIngridientName(name).stream().map(burgerMappers::toDto).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse createCommentByClient(Long clientId, CommentRequest commentRequest) {
        Optional<Client> client = clientRepository.findById(clientId);
        Comment comment = new Comment(null, commentRequest.getComment(), commentRequest.getDate());
        Comment saveComment = commentRepository.save(comment);
        List<Comment> commentList = client.get().getComments();
        commentList.add(saveComment);
        client.get().setComments(commentList);
        clientRepository.save(client.get());
        return commentMappers.toResponse(comment);
    }

    @Transactional
    public ShawarmaDto createShawarma(Long id, Long start, Long end, String type, Long code) {
        Shawarma shawarma = new Shawarma(type, code, new BigDecimal(0));
        Shawarma saveShawarma = shawarmaRepository.save(shawarma);
        List<Ingridient> ingridientList = ingridientRepository.selectIngridientsForCreate(start, end);
        ingridientRepository.updateCountIngridients(1L, start, end);
        BigDecimal price = ingridientRepository.getSumOfIngridients(start, end);
        saveShawarma.setPrice(price);
        saveShawarma.setIngridients(ingridientList);
        return shawarmaMappers.toDto(shawarmaRepository.save(saveShawarma));
    }
}
