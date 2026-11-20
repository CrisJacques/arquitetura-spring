package com.example.arquiteturaspring.montadora.configuration;

import com.example.arquiteturaspring.montadora.Motor;
import com.example.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Essa classe conterá alguns Beans que serão configurados e precisam ser registrados.
 */
@Configuration
public class MontadoraConfiguration {

    /**
     * Ao iniciar a aplicação, os objetos instanciados pelos métodos marcados com @Bean serão registrados como
     * Beans no container do Spring, ficando disponíveis para uso em qualquer lugar da aplicação, podendo ser
     * injetados como dependência sem precisar chamar new(). A injeção poderá ser feita pelo construtor da classe
     * ou usando a anotação @Autowired. Tudo isso só irá funcionar se essa classe tiver a anotação @Configuration.
     *
     * Registramos como Beans componentes complexos que precisaremos utilizar ao longo de toda a aplicação.
     *
     * O nome do método que cria o objeto será o nome do Bean. Dessa forma, abaixo estão sendo criados 3 Beans:
     * motorAspirado, motorEletrico e motorTurbo.
     *
     * Caso se deseje que o Bean tenha um nome diferente do nome do método, basta fazer @Bean(name = "nomeDesejado")
     *
     * Porém, no momento de injetar a dependência, o Spring não saberá qual desses Beans injetar, porque os 3
     * são do mesmo tipo (Motor). Dessa forma, será necessário utilizar Qualifiers para indicar ao Spring qual
     * deles deve ser injetado.
     *
     * Para isso, basta adicionar a anotação @Qualifier, indicando o nome do Bean que deve ser injetado. Por exemplo:
     * @Autowired
     * @Qualifier("motorEletrico")
     * private Motor motor;
     */
    @Bean
    public Motor motorAspirado() {
        var motor = new Motor();

        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(2.0);
        motor.setTipo(TipoMotor.ASPIRADO);

        return motor;
    }

    @Bean
    public Motor motorEletrico() {
        var motor = new Motor();

        motor.setCavalos(110);
        motor.setCilindros(3);
        motor.setModelo("TH-40");
        motor.setLitragem(1.4);
        motor.setTipo(TipoMotor.ELETRICO);

        return motor;
    }

    @Bean
    public Motor motorTurbo() {
        var motor = new Motor();

        motor.setCavalos(180);
        motor.setCilindros(3);
        motor.setModelo("XPTO-01");
        motor.setLitragem(1.5);
        motor.setTipo(TipoMotor.TURBO);

        return motor;
    }

}
