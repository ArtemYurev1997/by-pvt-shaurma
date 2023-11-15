//package by.pvt.shaurma.core.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@ComponentScan("by.pvt.shaurma.core")
//@EnableJpaRepositories("by.pvt.shaurma.core.repository")
//@EnableTransactionManagement
//@PropertySource("application.properties")
//public class HibernateConfig {
//
//        @Value("${server.url}")
//        private String url;
//
//        @Value("${server.driver}")
//        private String driver;
//
//        @Value("${server.login}")
//        private String login;
//
//        @Value("${server.password}")
//        private String password;
//
//        @Value("${hibernate.dialect}")
//        private String dialect;
//
//        @Value("${hibernate.show_sql}")
//        private boolean showSql;
//
//        @Value("${hibernate.format_sql}")
//        private boolean formatSql;
//
//        @Value("${hibernate.hbm2ddl.auto}")
//        private String hbm2ddl;
//
//        @Value("${hibernate.packagesToScan}")
//        private String packagesToScan;
//
//        @Bean
//        public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//            return new PropertySourcesPlaceholderConfigurer();
//        }
//
//        @Bean
//        public DataSource getDataSource() {
//            HikariConfig hikariConfig = new HikariConfig();
//            hikariConfig.setUsername(login);
//            hikariConfig.setPassword(password);
//            hikariConfig.setJdbcUrl(url);
//            hikariConfig.setDriverClassName(driver);
//            return new HikariDataSource(hikariConfig);
//        }
//
//        @Bean
//        public Properties getProperty() {
//            Properties properties = new Properties();
//            properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
//            properties.put("hibernate.format_sql", formatSql);
//            properties.put("hibernate.dialect", dialect);
//            properties.put("hibernate.show_sql", showSql);
//            return properties;
//        }
//
//        @Bean
//        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//            localContainerEntityManagerFactoryBean.setDataSource(getDataSource());
//            localContainerEntityManagerFactoryBean.setPackagesToScan(packagesToScan);
//            localContainerEntityManagerFactoryBean.setJpaProperties(getProperty());
//            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//            return localContainerEntityManagerFactoryBean;
//        }
//
//
//        @Bean
//        public PlatformTransactionManager transactionManager() {
//            JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//            jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//            return jpaTransactionManager;
//        }
//}
