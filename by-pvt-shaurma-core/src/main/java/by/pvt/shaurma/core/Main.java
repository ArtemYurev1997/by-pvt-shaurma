package by.pvt.shaurma.core;

import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.GoodMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.repository.impl.*;
import by.pvt.shaurma.core.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GoodDaoRepository goodDaoRepository = new GoodDaoRepository();
        OrderDaoRepository orderDaoRepository = new OrderDaoRepository();
        ClientDaoRepository clientDaoRepository = new ClientDaoRepository();
        AdminDaoRepository adminDaoRepository = new AdminDaoRepository();

//        clientDaoRepository.addClient(new Client(null, "Антон", "Антонов", "Anton57", "1323", "Client", LocalDate.of(2023, 5, 26),
//                LocalDate.of(2023, 9, 12), "+375334673368", new BigDecimal(37)));
//        adminDaoRepository.addUser(new Admin(null, "Артём", "Артёмов", "Artem1337", "1234", "Admin",  LocalDate.of(2023, 1, 21),  LocalDate.of(2023, 9, 26),
//                "Менеджер", new BigDecimal(1500)));
//
//        goodDaoRepository.addGood(new Good("Шаурма", 9042769L, "Лепешка, Курица, Пекинская капуста, Помидор маринованный, Огурец, Чесночный соус, Горчичный соус", new BigDecimal(6)));
//        goodDaoRepository.addGood(new Good("Шаурма", 1424280L, "Лепешка, Говядина, Пекинская капуста, Помидор маринованный, Огурец, Чесночный соус, Барбекю соус", new BigDecimal(7)));
//        goodDaoRepository.update(new Good( 1L,"Шаурма", 465370L, "Лепешка, Мраморная говядина, Пекинская капуста, Помидор маринованный, Огурец, Чесночный соус, Красный соус", new BigDecimal(6.5)));


        OrderMapper orderMapper = new OrderMapper();
        GoodMapper goodMapper = new GoodMapper();
        ClientMapper clientMapper = new ClientMapper();
        OrderService orderService = new OrderService(goodDaoRepository, orderDaoRepository, goodMapper, orderMapper, clientDaoRepository, clientMapper);
//        System.out.println(basketService.createBasket(3L, 1L, 3L));
//        System.out.println(basketService.createBasket(3L, 2L, 4L));
//        System.out.println(basketService.addGoodInBasket(3L, 5L, 4L));
//        System.out.println(orderService.createOrder(1L));
//        System.out.println(orderService.updateOrderToClient(1L, 1L));
//        System.out.println(orderService.addProductByOrder(2L, 1L));
//        System.out.println(orderService.changeStatus(1L, 1L, 3L));
//        System.out.println(orderDaoRepository.getSumCostOfGoodsInOrder());
        System.out.println(clientDaoRepository.getClientById(1L));

    }
}
