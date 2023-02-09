package com.mrsnow.petstore;

import com.mrsnow.petstore.filters.FilterItem;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.mrsnow.petstore")
@MapperScan("com.mrsnow.petstore.mapper*")
@EnableTransactionManagement
public class PetStoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PetStoreApplication.class, args);
    }

}
