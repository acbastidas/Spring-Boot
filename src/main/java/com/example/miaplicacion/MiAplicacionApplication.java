package com.example.miaplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.example.miaplicacion.repository")
public class MiAplicacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiAplicacionApplication.class, args);
    }
}