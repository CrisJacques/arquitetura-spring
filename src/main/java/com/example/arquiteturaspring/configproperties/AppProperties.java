package com.example.arquiteturaspring.configproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe que retorna os valores das propriedades que estão dentro de app.config no application.yml.
 *
 * Tem setters para que o Spring faça a injeção dos valores e getters para que esses valores possam ser lidos ao
 * longo de toda a aplicação.
 *
 * Essa abordagem evita a necessidade de usar @Value("${caminho.da.propriedade}") em cada variável que precisar
 * receber o valor de uma propriedade do application.yml.
 */
@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppProperties {
    /**
     * Os nomes dos atributos devem ser os mesmos das variáveis do application.yml
     */
    private String variavel;
    private Integer valor1;

    public String getVariavel() {
        return variavel;
    }

    public void setVariavel(String variavel) {
        this.variavel = variavel;
    }

    public Integer getValor1() {
        return valor1;
    }

    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }
}
