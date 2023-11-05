package by.pvt.shaurma.core;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.contract.BasketApi;
import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.core.mapper.AdminMapper;
import by.pvt.shaurma.core.mapper.BasketMapper;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.repository.impl.*;
import by.pvt.shaurma.core.service.AdminService;
import by.pvt.shaurma.core.service.BasketService;
import by.pvt.shaurma.core.service.ClientService;
import by.pvt.shaurma.core.service.OrderService;
import org.hibernate.SessionFactory;

public class ApplicationContext {
    private static ApplicationContext applicationContext;

    private final AdminApi adminService;

    private final ClientApi clientService;

    private final OrderApi orderService;

    private final BasketApi basketService;

    private static SessionFactory sessionFactory;

    public ApplicationContext() {
        adminService = new AdminService(new AdminDaoRepository(), new AdminMapper(), sessionFactory);
        clientService = new ClientService(new ClientDaoRepository(), new ClientMapper(), sessionFactory);
        orderService = new OrderService(new OrderDaoRepository(), new OrderMapper(), new ClientDaoRepository(), new ClientMapper(), sessionFactory);
        basketService = new BasketService(new BasketShawarmaDaoRepository(), new BasketBurgerDaoRepository(), new BasketDrinkDaoRepository(), new OrderDaoRepository(),
                new ShawarmaDaoRepository(), new BurgerDaoRepository(), new DrinkDaoRepository(), new BasketMapper());
    }

    public static synchronized ApplicationContext getInstance() {
        if(applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public AdminApi getAdminService() {
        return adminService;
    }

    public ClientApi getClientService() {
        return clientService;
    }

    public OrderApi getOrderService() {
        return orderService;
    }

    public BasketApi getBasketService() {
        return basketService;
    }
}
