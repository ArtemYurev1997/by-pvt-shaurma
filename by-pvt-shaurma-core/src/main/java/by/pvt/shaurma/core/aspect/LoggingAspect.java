package by.pvt.shaurma.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut(value = "execution(* by.pvt.shaurma.core.controller.OrderController.*(..))")
    public void logOrder() {
    }

    @Before(value = "logOrder()")
    public void beforeLogging() {
        log.info("Стартовал запрос из контроллера Order");
    }

    @Around(value = "logOrder()")
    public Object loggingOrderController(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;
        try {
            log.info("Стартовал запрос из контроллера OrderController, Time: {}", LocalDateTime.now());
            object = joinPoint.proceed();
            log.info("Запрос из контроллера OrderController завершён, Time: {}", LocalDateTime.now());
        }
        catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return object;
    }

    @AfterThrowing(value = "logOrder()", throwing = "ex")
    public void afterLogging(Throwable ex) {
        log.error(ex.getMessage(), ex);
    }
}
