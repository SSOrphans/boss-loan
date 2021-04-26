package org.ssor.boss.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Derrian Harris
 */
@SpringBootApplication
@ComponentScan("org.ssor.boss.core.repository")
public class BossLoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BossLoanServiceApplication.class, args);
    }
}
