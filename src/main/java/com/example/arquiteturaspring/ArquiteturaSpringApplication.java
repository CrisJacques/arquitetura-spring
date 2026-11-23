package com.example.arquiteturaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * A anotação @EnableConfigurationProperties permite a criação de classes de Configuration Properties, que receberão as
 * propriedades que estão configuradas no application.yml para poderem ser usadas ao longo de toda a aplicação
 */
@SpringBootApplication
@EnableConfigurationProperties
public class ArquiteturaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquiteturaSpringApplication.class, args);
    }

}
