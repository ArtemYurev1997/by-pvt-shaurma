package by.pvt.shaurma.core;

import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.mapper.GoodMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.mapper.UserMapper;
import by.pvt.shaurma.core.repository.impl.BasketDaoRepository;
import by.pvt.shaurma.core.repository.impl.GoodDaoRepository;
import by.pvt.shaurma.core.repository.impl.OrderDaoRepository;
import by.pvt.shaurma.core.repository.impl.UserDaoRepository;
import by.pvt.shaurma.core.service.BasketService;
import by.pvt.shaurma.core.service.OrderService;

public class Main {
    public static void main(String[] args) {
        GoodDaoRepository goodDaoRepository = new GoodDaoRepository();
        UserDaoRepository userDaoRepository = new UserDaoRepository();
        OrderDaoRepository orderDaoRepository = new OrderDaoRepository();

//        userDaoRepository.addUser(new User(null, "Антон", "Антонов", "Anton1234", "1234", "Client"));
//        goodDaoRepository.addGood(new Good("Шаурма", 433561L, "Лепешка, Говядина, Капуста, Помидор маринованный, Огурец, Чесночный соус, Барбекю соус>", new BigDecimal(7.5)));
//        goodDaoRepository.update(new Good( 1L,"Шаурма", 465370L, "Лепешка, Мраморная говядина, Пекинская капуста, Помидор маринованный, Огурец, Чесночный соус, Красный соус", new BigDecimal(6.5)));

        BasketDaoRepository basketDaoRepository = new BasketDaoRepository();
        BasketMapper basketMapper = new BasketMapper();
        OrderMapper orderMapper = new OrderMapper();
        GoodMapper goodMapper = new GoodMapper();
        UserMapper userMapper = new UserMapper();
        BasketService basketService = new BasketService(goodDaoRepository, basketDaoRepository, basketMapper, goodMapper);
        OrderService orderService = new OrderService(goodDaoRepository, basketService, orderDaoRepository, goodMapper, orderMapper, basketMapper, userDaoRepository, userMapper);
//        System.out.println(basketService.createBasket(3L, 1L, 3L));
//        System.out.println(basketService.createBasket(3L, 2L, 4L));
//        System.out.println(basketService.addGoodInBasket(3L, 5L, 4L));
//        System.out.println(orderService.createOrder(3L, 1L, 4L));
    }
}
