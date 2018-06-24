package net.c0nan.agic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"net.c0nan"})
public class TestAPIApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TestAPIApplication.class, args);
    }
}
