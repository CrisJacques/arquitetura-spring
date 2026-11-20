package com.example.arquiteturaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArquiteturaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquiteturaSpringApplication.class, args);

        /**
         * Outra forma de inicializar a aplicação (no lugar de SpringApplication.run(ProdutosApiApplication.class, args);)
         * é usar o SpringApplicationBuilder, que permite uma série de configurações adicionais:
         *
         * SpringApplicationBuilder builder = new SpringApplicationBuilder(ProdutosApiApplication.class);
         *
         * // Fazendo dessa forma, é possível fazer algumas customizações na inicialização, por exemplo:
         * builder.bannerMode(Banner.Mode.OFF) // desligando o banner do Spring na inicialização
         *
         * // Além disso, é possível selecionar o profile (ou seja, o ambiente) em que a aplicação irá rodar, por exemplo:
         * builder.profiles("producao");
         *
         * // E no final, é só mandar rodar
         * builder.run(args);
         *
         * // Depois que a aplicação inicializa também é possível fazer outras ações, por exemplo obter um Bean a partir
         * do ApplicationContext
         * ConfigurableApplicationContext applicationContext = builder.context();
         * var produtoRepository = applicationContext.getBean("produtoRepository");
         */
    }

}
