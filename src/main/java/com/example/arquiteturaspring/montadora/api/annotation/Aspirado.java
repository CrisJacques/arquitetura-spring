package com.example.arquiteturaspring.montadora.api.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O Spring permite que a gente crie anotações para agregar outras anotações dele para tornar o código
 * mais limpo e agradável de ler. No caso abaixo, estamos criando uma anotação para o @Qualifier("motorAspirado").
 *
 * Significado das anotações:
 * @Retention(RetentionPolicy.RUNTIME) - Retention padrão.
 * @Target({ElementType.FIELD, ElementType.METHOD}) - onde a anotação se aplica. Neste caso, ela se
 * aplica a atributos e métodos, respectivamente, pois é por onde fazemos injeção de dependências.
 * @Qualifier("motorAspirado") - Essa anotação será a implementação desse Qualifier
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Qualifier("motorAspirado")
public @interface Aspirado {
}
