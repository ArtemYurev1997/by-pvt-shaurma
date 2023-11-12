package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.repository.BasketBurgerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class BasketBurgerDaoRepository implements BasketBurgerDao {
    @Autowired
    private SessionFactory sessionFactory;

//    public BasketBurgerDaoRepository() {
//        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
//    }


    @Override
    public List<BasketBurger> getAllBaskets() {
        Session session = sessionFactory.openSession();
        return session.createQuery("select b from BasketBurger b", BasketBurger.class).getResultList();
    }

    @Override
    public void add(BasketBurger basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(basket);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long orderId, Long burgerId) {
        Session session = sessionFactory.openSession();
        BasketBurger basket = session.createQuery("select b from BasketBurger b where b.id.orderId=:orderId and b.id.burgerId=:burgerId", BasketBurger.class).setParameter("orderId", orderId).setParameter("burgerId", burgerId).getSingleResult();
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
        session.close();
    }

    public BigDecimal totalPriceBurger(Long orderId) {
        Session session = sessionFactory.openSession();
        BigDecimal price = session.createQuery("select sum(d.cost) from BasketBurger d where d.id.orderId=:orderId", BigDecimal.class).setParameter("orderId", orderId).getSingleResult();
        session.close();
        return price;
    }

    public Long totalCountBurger(Long orderId) {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select sum(d.count) from BasketBurger d where d.id.orderId=:orderId", Long.class).setParameter("orderId", orderId).getSingleResult();
        session.close();
        return count;
    }
}
