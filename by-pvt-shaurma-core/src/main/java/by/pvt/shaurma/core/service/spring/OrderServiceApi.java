package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.*;
import by.pvt.shaurma.api.enums.Status;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.exception.PaymentException;
import by.pvt.shaurma.core.exception.ProgramException;
import by.pvt.shaurma.core.mapper.spring.*;
import by.pvt.shaurma.core.repository.spring.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceApi implements OrderApi {
    private final OrderRepository orderRepository;
    private final OrderMappers orderMappers;
    private final ClientRepository clientRepository;
    private final ClientMappers clientMappers;
    private final CommentRepository commentRepository;
    private final CommentMappers commentMappers;
    private final BasketApi basketApi;

    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {
        return orderMappers.toResponse(orderRepository.save(orderMappers.toEntity(orderRequest)));
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderResponse findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return orderMappers.toResponse(order.get());
        }
        else throw new ProgramException("Такого заказа не существует");
    }

    public Page<OrderResponse> getOrdersPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return orderRepository.findAll(pageable).map(orderMappers::toResponse);
    }

    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream().map(orderMappers::toResponse).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order;
        Client client = clientMappers.toEntity(orderRequest.getUserId());
        Optional<Order> createOrder = orderRepository.getOrdersByUserId(client.getId()).stream().filter(order1 -> Objects.equals(order1.getStatus(), Status.UNFORMED.getName())).findFirst();
        if(createOrder.isEmpty()) {
            order = new Order(null, client, orderRequest.getCount(), orderRequest.getCost(), orderRequest.getDate(), orderRequest.getTelephone(), orderRequest.getAddress(), orderRequest.getComment(), Status.UNFORMED.getName(), orderRequest.getPayment());
            orderRepository.save(order);
            return orderMappers.toResponse(order);
        }
        else {
            order = createOrder.get();
        }
        return orderMappers.toResponse(order);
    }

    @Override
    public OrderResponse updateOrderToClient(OrderRequest orderRequest) {
        Optional<Order> order = orderRepository.findById(orderRequest.getId());
        Optional<Client> client = clientRepository.findById(orderRequest.getUserId().getId());
        if(order.isPresent() && client.isPresent()) {
            order.get().setUserId(client.get());
            orderRepository.save(order.get());
        }
        else throw new ProgramException("Значения клиента и заказа не являются null!");
        return orderMappers.toResponse(order.get());
    }

    @Override
    public OrderResponse checkOut(OrderRequest orderRequest) {
        Optional<Order> order = orderRepository.findById(orderRequest.getId());
        if(order.isPresent()) {
            Client user = order.get().getUserId();
            order.get().setDate(LocalDate.now());
            order.get().setTelephone(user.getTelephone());
            order.get().setAddress(user.getAddress());
            order.get().setComment(user.getComments().toString());
            order.get().setPayment(Status.PAY.getName());
            order.get().setStatus(Status.DONE.getName());
            orderRepository.save(order.get());
        }
        else throw new ProgramException("Значения клиента и заказа не являются null!");
        return orderMappers.toResponse(order.get());
    }

    @Transactional
    @Override
    public OrderResponse payment(OrderRequest orderRequest) {
        Long orderId = orderRequest.getId();
        Long userId = orderRequest.getUserId().getId();
        BigDecimal sum = orderRequest.getCost();
        BigDecimal cost = orderRepository.getCostOfOrder(orderId, userId);
        int result = sum.compareTo(cost);
        if (result == 0) {
            System.out.println("Оплата прошла успешно! Заказ Оформлен!");
            return checkOut(orderRequest);
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
    public OrderResponse changeStatus(OrderRequest orderRequest) {
        Long orderId = orderRequest.getId();
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            order.get().setStatus(orderRequest.getStatus());
            orderRepository.save(order.get());
        }
        else throw new ProgramException("Значения клиента и заказа не являются null!");
        return orderMappers.toResponse(order.get());
    }

    public OrderResponse addCostAndCountToOrder(OrderRequest orderRequest) {
        Optional<Order> order = orderRepository.findById(orderRequest.getId());
        if(order.isPresent()) {
            order.get().setCost(basketApi.totalPriceAllBasketsForOrder(orderRequest.getId()));
            order.get().setCount(basketApi.totalCountAllBasketsForOrder(orderRequest.getId()));
            order.get().setStatus(Status.WAITING.getName());
            orderRepository.save(order.get());
        }
        else throw new ProgramException("Значения клиента и заказа не являются null!");
        return orderMappers.toResponse(order.get());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderMappers.toResponse(orderRepository.findById(id).get());
    }

    @Transactional
    public CommentResponse createCommentByClient(CommentRequest commentRequest) {
        Optional<Client> clientId = clientRepository.findById(commentRequest.getClientId());
        Comment comment = null;
        if(clientId.isPresent()) {
            comment = new Comment(null, commentRequest.getComment(), LocalDate.now(), clientId.get());
        }
        Comment saveComment = commentRepository.save(comment);
        Optional<Client> client = clientRepository.findById(saveComment.getClient().getId());
        if(client.isPresent()) {
            List<Comment> commentList = client.get().getComments();
            commentList.add(saveComment);
            client.get().setComments(commentList);
            clientRepository.save(client.get());
        }
        return commentMappers.toResponse(saveComment);
    }
}
