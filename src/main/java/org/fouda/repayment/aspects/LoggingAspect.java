package org.fouda.repayment.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @AfterThrowing(value = "execution(* org.fouda.repayment.services.RepaymentServiceFacade.*(..))", throwing = "exception")
    public void before(Exception exception) {
        log.error("Business layer exception", exception);
    }
}
