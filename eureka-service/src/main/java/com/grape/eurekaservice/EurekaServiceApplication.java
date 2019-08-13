package com.grape.eurekaservice;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }

    @Value("${message}")
    private String message;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("custom prop "+ message);
    }
}
