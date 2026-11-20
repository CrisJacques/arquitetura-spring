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
     * Ao iniciar a aplicação, o Spring irá instanciar esse Bean e vai registrar no Container para que
     * possa ser utilizado. Registramos como Beans componentes complexos que precisaremos utilizar ao longo
     * de toda a aplicação.
     */
    @Bean
    public Motor motor() {
        var motor = new Motor();

        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(2.0);
        motor.setTipo(TipoMotor.ASPIRADO);

        return motor;
    }

}
