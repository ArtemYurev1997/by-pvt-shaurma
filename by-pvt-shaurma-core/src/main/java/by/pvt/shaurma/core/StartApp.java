package by.pvt.shaurma.core;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.CommentRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories("by.pvt.shaurma.core.repository")
public class StartApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(StartApp.class, args);

//        OrderApi orderApi = applicationContext.getBean("orderServiceApi", OrderApi.class);
//        CommentRequest commentRequest = new CommentRequest();
//        commentRequest.setComment("Жду к 7 часам!");
//        commentRequest.setDate(LocalDateTime.now());
//        System.out.println(orderApi.createCommentByClient(1L, commentRequest));
    }
}
