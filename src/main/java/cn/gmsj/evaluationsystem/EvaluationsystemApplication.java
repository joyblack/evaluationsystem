package cn.gmsj.evaluationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 13562
 */
@SpringBootApplication
@EnableScheduling
public class EvaluationsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationsystemApplication.class, args);
    }

}
