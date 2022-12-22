package com.joyou.patrol;

import com.joyou.patrol.annotation.PatrolApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@PatrolApplication
@SpringBootApplication
public class IntegrationOpsPatrolApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationOpsPatrolApplication.class, args);
    }

}
