package by.pvt.shaurma.core.config;

import by.pvt.shaurma.core.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateJavaConfiguration {
    private final static StandardServiceRegistryBuilder serviceRegistryBuilder;

    private final static Configuration configuration;

    static {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.use_sql_comments", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/shaurmadb");  //создать новую бд
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "sa");
        properties.setProperty("hibernate.show_sql", "true");

        configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Good.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Basket.class);
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Client.class);
        serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(properties);
    }

    //это объект типа EntityManager
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
        return sessionFactory;
    }
}
