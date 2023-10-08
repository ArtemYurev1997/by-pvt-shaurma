package by.pvt.shaurma.core;

import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.repository.BasketDrinkDao;
import by.pvt.shaurma.core.repository.impl.*;
import by.pvt.shaurma.core.service.BasketService;
import by.pvt.shaurma.core.service.OrderService;

public class Main {
    public static void main(String[] args) {
        ShawarmaDaoRepository shawarmaDaoRepository = new ShawarmaDaoRepository();
        BurgerDaoRepository burgerDaoRepository = new BurgerDaoRepository();
        OrderDaoRepository orderDaoRepository = new OrderDaoRepository();
        ClientDaoRepository clientDaoRepository = new ClientDaoRepository();
        AdminDaoRepository adminDaoRepository = new AdminDaoRepository();
        BasketShawarmaDaoRepository basketShawarmaDaoRepository = new BasketShawarmaDaoRepository();
        BasketBurgerDaoRepository basketBurgerDaoRepository = new BasketBurgerDaoRepository();
        BasketDrinkDaoRepository basketDrinkDaoRepository = new BasketDrinkDaoRepository();
        DrinkDaoRepository drinkDaoRepository = new DrinkDaoRepository();
        BasketMapper basketMapper = new BasketMapper();

//        clientDaoRepository.addClient(new Client(null, "Антон", "Антонов", "Anton57", "1323", "Client", LocalDate.of(2023, 5, 26),
//                LocalDate.of(2023, 9, 12), "+375334673368", new BigDecimal(37)));
//        adminDaoRepository.addUser(new Admin(null, "Артём", "Артёмов", "Artem1337", "1234", "Admin",  LocalDate.of(2023, 1, 21),  LocalDate.of(2023, 9, 26),
//                "Менеджер", new BigDecimal(1500)));


        OrderMapper orderMapper = new OrderMapper();
        ClientMapper clientMapper = new ClientMapper();
        OrderService orderService = new OrderService(orderDaoRepository, orderMapper, clientDaoRepository, clientMapper);
        BasketService basketService = new BasketService(basketShawarmaDaoRepository, basketBurgerDaoRepository, basketDrinkDaoRepository, orderDaoRepository, shawarmaDaoRepository, burgerDaoRepository, drinkDaoRepository, basketMapper);
//        System.out.println(basketService.createBasket(3L, 1L, 3L));
//        System.out.println(basketService.createBasket(3L, 2L, 4L));
//        System.out.println(basketService.addGoodInBasket(3L, 5L, 4L));
//        System.out.println(orderService.createOrder(1L));
//        System.out.println(orderService.updateOrderToClient(1L, 1L));
//        System.out.println(orderService.addProductByOrder(2L, 1L));
//        System.out.println(orderService.changeStatus(1L, 1L, 3L));
//        System.out.println(orderDaoRepository.getSumCostOfGoodsInOrder());
//        System.out.println(clientDaoRepository.getClientById(1L));
//
//        IngridientDaoRepository ingridientDaoRepository = new IngridientDaoRepository();
//        Ingridient ingridient1 = new Ingridient(null, "Курица", new BigDecimal(3), 100L);
//        Ingridient ingridient2 = new Ingridient(null, "Лаваш", new BigDecimal(1.5), 100L);
//        Ingridient ingridient3 = new Ingridient(null, "Капуста", new BigDecimal(1), 100L);
//        Ingridient ingridient4 = new Ingridient(null, "Чесночный Соус", new BigDecimal(1), 100L);
//        Ingridient ingridient5 = new Ingridient(null, "Картофель", new BigDecimal(1.5), 100L);
//        Ingridient ingridient6 = new Ingridient(null, "Помидор", new BigDecimal(1), 100L);
//        Ingridient ingridient7 = new Ingridient(null, "Огурец", new BigDecimal(1), 100L);
//        Ingridient ingridient8 = new Ingridient(null, "Рыба", new BigDecimal(2.5), 100L);
//        Ingridient ingridient9 = new Ingridient(null, "Говядина", new BigDecimal(3), 100L);
//        Ingridient ingridient10 = new Ingridient(null, "Красный соус", new BigDecimal(1), 100L);
//        Ingridient ingridient11 = new Ingridient(null, "Халапеньо", new BigDecimal(1.5), 100L);
//        Ingridient ingridient12 = new Ingridient(null, "Салат", new BigDecimal(1), 100L);
//        Ingridient ingridient13 = new Ingridient(null, "Сыр", new BigDecimal(2), 100L);
//        Ingridient ingridient14 = new Ingridient(null, "Булка", new BigDecimal(1.5), 100L);
//
//        ingridientDaoRepository.addIngridient(ingridient1);
//        ingridientDaoRepository.addIngridient(ingridient2);
//        ingridientDaoRepository.addIngridient(ingridient3);
//        ingridientDaoRepository.addIngridient(ingridient4);
//        ingridientDaoRepository.addIngridient(ingridient5);
//        ingridientDaoRepository.addIngridient(ingridient6);
//        ingridientDaoRepository.addIngridient(ingridient7);
//        ingridientDaoRepository.addIngridient(ingridient8);
//        ingridientDaoRepository.addIngridient(ingridient9);
//        ingridientDaoRepository.addIngridient(ingridient10);
//        ingridientDaoRepository.addIngridient(ingridient11);
//        ingridientDaoRepository.addIngridient(ingridient12);
//        ingridientDaoRepository.addIngridient(ingridient13);
//        ingridientDaoRepository.addIngridient(ingridient14);
//
//        Ingridient ingridient1 = ingridientDaoRepository.getIngridientById(1L);
//        Ingridient ingridient2 = ingridientDaoRepository.getIngridientById(2L);
//        Ingridient ingridient3 = ingridientDaoRepository.getIngridientById(3L);
//        Ingridient ingridient4 = ingridientDaoRepository.getIngridientById(4L);
//        Ingridient ingridient5 = ingridientDaoRepository.getIngridientById(5L);
//        Ingridient ingridient6 = ingridientDaoRepository.getIngridientById(6L);
//        Ingridient ingridient7 = ingridientDaoRepository.getIngridientById(7L);
//        Ingridient ingridient8 = ingridientDaoRepository.getIngridientById(8L);
//        Ingridient ingridient9 = ingridientDaoRepository.getIngridientById(9L);
//        Ingridient ingridient10 = ingridientDaoRepository.getIngridientById(10L);
//        Ingridient ingridient11 = ingridientDaoRepository.getIngridientById(11L);
//        Ingridient ingridient12 = ingridientDaoRepository.getIngridientById(12L);
//        Ingridient ingridient13 = ingridientDaoRepository.getIngridientById(13L);
//        Ingridient ingridient14 = ingridientDaoRepository.getIngridientById(14L);
//
//        Shawarma shawarma1 = new Shawarma( "Классическая", 232424L, new BigDecimal(7),null);
//        Shawarma shawarma2 = new Shawarma( "Мексика", 153542L, null, null);
//        Shawarma shawarma3 = new Shawarma( "Бульбаш", 734457L, null, null);
//        Shawarma shawarma4 = new Shawarma( "От Шефа", 972007L, null, null);
//        Shawarma shawarma5 = new Shawarma( "Сырная", 587703L, null, null);
//        Shawarma shawarma6 = new Shawarma( "Арабская", 477123L, null, null);
//        Shawarma shawarma7 = new Shawarma( "Фалафель", 358944L, null, null);
//        Shawarma shawarma8 = new Shawarma( "Вегетарианская", 107844L, null, null);
//        Shawarma shawarma9 = new Shawarma( "Сытная", 251126L, null, null);
//        shawarmaDaoRepository.addShawarma(shawarma1);
//        shawarmaDaoRepository.addShawarma(shawarma2);
//        shawarmaDaoRepository.addShawarma(shawarma3);
//        shawarmaDaoRepository.addShawarma(shawarma4);
//        shawarmaDaoRepository.addShawarma(shawarma5);
//        shawarmaDaoRepository.addShawarma(shawarma6);
//        shawarmaDaoRepository.addShawarma(shawarma7);
//        shawarmaDaoRepository.addShawarma(shawarma8);
//        shawarmaDaoRepository.addShawarma(shawarma9);
//
//        Burger burger1 = new Burger("Гамбургер", 3345266L, null, null);
//        Burger burger2 = new Burger("Чизбургер", 7409462L, null, null);
//        Burger burger3 = new Burger("Фишбургер", 6241679L, null, null);
//        burgerDaoRepository.addBurger(burger1);
//        burgerDaoRepository.addBurger(burger2);
//        burgerDaoRepository.addBurger(burger3);

//        Drink drink1 = new Drink("Fanta", 142231L, new BigDecimal(2));
//        Drink drink2 = new Drink("Sprite", 632756L, new BigDecimal(2));
//        Drink drink3 = new Drink("Coca-Cola", 709453L, new BigDecimal(2));
//        drinkDaoRepository.addDrink(drink1);
//        drinkDaoRepository.addDrink(drink2);
//        drinkDaoRepository.addDrink(drink3);

//        orderDaoRepository.createShawarma(4L, 1L, 7L);
//        System.out.println(shawarmaDaoRepository.getShawarmaById(4L));

//        System.out.println(orderService.createOrder());
//        System.out.println(orderService.updateOrderToClient(1L, 2L));


//        System.out.println(basketService.createEmptyBasket());
//        System.out.println(basketService.createBasketWithShawarma( 2L, 1L, 3L, 1L, 3L));
    }
}
