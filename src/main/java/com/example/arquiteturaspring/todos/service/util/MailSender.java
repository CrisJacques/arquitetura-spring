package com.example.arquiteturaspring.todos.service.util;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviar(String mensagem) {
        System.out.println("Enviado email: " + mensagem);
    }
}
