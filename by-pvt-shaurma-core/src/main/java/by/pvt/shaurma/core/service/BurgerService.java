package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.BurgerApi;
import by.pvt.shaurma.api.dto.BurgerDto;
import by.pvt.shaurma.core.entity.Burger;
import by.pvt.shaurma.core.mapper.BurgerMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class BurgerService implements BurgerApi {
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public BurgerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<BurgerDto> getBurgersDtoByIngridient(String name) {
        BurgerMapper burgerMapper = new BurgerMapper();
        session = sessionFactory.openSession();
        List<Burger> burgers = session.createQuery("select b from Burger s join b.ingridients i where i.name=:name", Burger.class).setParameter("name", name).getResultList();
        return burgers.stream().map(burgerMapper::toBurgerDto).collect(Collectors.toList());
    }
}
