//package by.pvt.shaurma.core.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@ComponentScan("by.pvt.shaurma.core")
//@EnableTransactionManagement
//@PropertySource("application.properties")
//public class HibernateSpringConfiguration {
//    @Bean
//    public static DataSource dataSource(@Value("${server.url}") String url, @Value("${server.driver}") String driver,
//                                        @Value("${server.login}") String login, @Value("${server.password}") String password) {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(driver);
//        hikariConfig.setJdbcUrl(url);
//        hikariConfig.setUsername(login);
//        hikariConfig.setPassword(password);
//        return new HikariDataSource(hikariConfig);
//    }
//
//    @Bean
//    public Properties hibernateProperties(@Value("${hibernate.dialect}") String dialect, @Value("${hibernate.format_sql}") boolean formatSql,
//                                          @Value("${hibernate.show_sql}") boolean showSql, @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl) {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", dialect);
//        properties.put("hibernate.format_sql", formatSql);
//        properties.put("hibernate.show_sql", showSql);
//        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
//        return properties;
//    }
//
//    @Bean
//    public SessionFactory sessionFactory(DataSource dataSource, @Value("${hibernate.packagesToScan}") String packagesToScan, Properties properties) throws Exception {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource);
//        localSessionFactoryBean.setPackagesToScan(packagesToScan);
//        localSessionFactoryBean.setHibernateProperties(properties);
//        localSessionFactoryBean.afterPropertiesSet();
//        return localSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
//        return new HibernateTransactionManager(sessionFactory);
//    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//}
