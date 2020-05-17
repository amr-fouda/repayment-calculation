package org.fouda.repayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RepaymentCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepaymentCalculatorApplication.class, args);
    }

}
