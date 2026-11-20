package com.example.arquiteturaspring.montadora.api;

import com.example.arquiteturaspring.montadora.CarroStatus;
import com.example.arquiteturaspring.montadora.Chave;
import com.example.arquiteturaspring.montadora.HondaHRV;
import com.example.arquiteturaspring.montadora.Motor;
import com.example.arquiteturaspring.montadora.api.annotation.Aspirado;
import com.example.arquiteturaspring.montadora.api.annotation.Eletrico;
import com.example.arquiteturaspring.montadora.api.annotation.Turbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta classe demonstra 3 formas de indicar qual Bean deve ser injetado como dependência quando existe mais de um
 * Bean do mesmo tipo. Tais formas são listadas na mesma ordem dos exemplos abaixo:
 * 1. Não informar qual Bean, mas estabelecer um padrão através da anotação @Primary na classe @Configuration
 * (MontadoraConfiguration.java).
 * 2. Informar explicitamente qual Bean através do @Qualifier. Neste caso, o Bean padrão é ignorado pelo Spring.
 * 3. Informar explicitamente qual Bean através de uma anotação customizada, que engloba @Qualifier. O efeito prático
 * é o mesmo da opção 2, mas o código fica mais limpo.
 */
@RestController
@RequestMapping("/carros")
public class TesteFabricaController {
    @Autowired
    private Motor aspirado;

    @Autowired
    @Qualifier("motorEletrico")
    private Motor eletrico;

    @Autowired
    @Turbo
    private Motor turbo;

    /**
     * Neste caso, o Motor a ser injetado como dependência será o Bean motorAspirado, pois não foi indicado em
     * "private Motor aspirado" qual o Bean que deveria ser injetado e há mais de um do mesmo tipo. Como o Bean
     * motorAspirado foi marcado como @Primary na classe MontadoraConfiguration, ele será o escolhido.
     */
    @PostMapping("/aspirado")
    public CarroStatus ligarCarroAspirado(@RequestBody Chave chave) {
        var carro = new HondaHRV(aspirado);
        return carro.darIgnicao(chave);
    }

    /**
     * Neste exemplo, o Motor a ser injetado como dependência será o Bean motorEletrico, pois foi indicado
     * explicitamente através da anotação @Qualifier (nesse caso, o Spring ignora o Bean padrão motorAspirado).
     */
    @PostMapping("/eletrico")
    public CarroStatus ligarCarroEletrico(@RequestBody Chave chave) {
        var carro = new HondaHRV(eletrico);
        return carro.darIgnicao(chave);
    }

    /**
     * Neste exemplo, o Motor a ser injetado como dependência será o Bean motorTurbo, pois foi indicado
     * explicitamente através da anotação @Turbo (que engloba a anotação @Qualifier("motorTurbo")). Aqui o efeito
     * prático é o mesmo de usar diretamente @Qualifier("motorTurbo"), mas foi criada a anotação @Turbo para deixar o
     * código mais limpo.
     */
    @PostMapping("/turbo")
    public CarroStatus ligarCarroTurbo(@RequestBody Chave chave) {
        var carro = new HondaHRV(turbo);
        return carro.darIgnicao(chave);
    }

}
