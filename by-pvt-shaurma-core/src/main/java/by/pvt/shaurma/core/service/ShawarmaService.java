package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.ShawarmaApi;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import by.pvt.shaurma.core.entity.Ingridient;
import by.pvt.shaurma.core.entity.Shawarma;
import by.pvt.shaurma.core.mapper.ShawarmaMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ShawarmaService implements ShawarmaApi {
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public ShawarmaService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ShawarmaDto> getShawarmaDtoByIngridient(String name) {
        ShawarmaMapper shawarmaMapper = new ShawarmaMapper();
        session = sessionFactory.openSession();
        List<Shawarma> shawarmas = session.createQuery("select s from Shawarma s join s.ingridients i where i.name=:name", Shawarma.class).setParameter("name", name).getResultList();
        return shawarmas.stream().map(shawarmaMapper::toShawarmaDto).collect(Collectors.toList());
    }

    public ShawarmaDto createShawarma( Long start, Long end, String type, Long code) {
        ShawarmaMapper shawarmaMapper = new ShawarmaMapper();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Shawarma shawarma = new Shawarma(type, code, new BigDecimal(0));
        List<Ingridient> ingridients = session.createQuery("select i from Ingridient i where i.id>=:start and i.id<=:end", Ingridient.class).setParameter("start", start).setParameter("end", end).getResultList();
        int query = session.createQuery("update Ingridient i set i.total=i.total-:decrement where i.id>=:start and i.id<=:end", Ingridient.class).setParameter("decrement", 1L).setParameter("start", start).setParameter("end", end).executeUpdate();
        BigDecimal price = session.createQuery("select sum(i.price) from Ingridient i where i.id>=:start and i.id<=:end", BigDecimal.class).setParameter("start", start).setParameter("end", end).getSingleResult();
        shawarma.setPrice(price);
        shawarma.setIngridients(ingridients);
        session.persist(shawarma);
        session.getTransaction().commit();
        session.close();
        return shawarmaMapper.toShawarmaDto(shawarma);
    }
}
