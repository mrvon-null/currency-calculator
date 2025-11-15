package com.von.curcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurcalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurcalcApplication.class, args);
    }

}
