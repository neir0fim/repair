package com.kuzin.repair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**application starter class.*/

@SpringBootApplication(scanBasePackages = {"com.kuzin.web", "com.kuzin.repair",
    "com.kuzin.entity", "com.kuzin.service"})
public class RepairApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepairApplication.class, args);
    }

}
