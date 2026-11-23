package com.example.arquiteturaspring.leituraproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // O nome do Bean correspondente à instância dessa classe será exemploValue
public class ExemploValue {

    /**
     * O atributo variavel está recebendo o valor de app.config.variavel do application.yml (ou seja, X)
     */
    @Value("${app.config.variavel}")
    private String variavel;

    public void imprimirVariavel() {
        System.out.println(variavel);
    }
}
