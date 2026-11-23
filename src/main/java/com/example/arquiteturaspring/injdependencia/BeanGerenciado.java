package com.example.arquiteturaspring.injdependencia;

import com.example.arquiteturaspring.configproperties.AppProperties;
import com.example.arquiteturaspring.todos.entity.TodoEntity;
import com.example.arquiteturaspring.todos.service.util.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Beans gerenciados são as instâncias de @RestController, @Service, @Repository,
 * @Component e @Beans que serão gerenciadas pelo Spring, podendo ser utilizadas
 * em qualquer ponto do código.
 *
 * Esta classe reúne exemplos das 3 formas de fazer injeção de dependência (ou seja,
 * no caso do Spring, dos Beans gerenciados):
 * - Injeção via propriedade
 * - Injeção via setter
 * - Injeção via construtor
 */
@Component
public class BeanGerenciado {

    /**
     * Opção 1: Injeção via propriedade, usando @Autowired, sem precisar de construtor.
     *
     * Essa opção é a mais simples, mas é a menos recomendada, pois não denota nem obrigatoriedade
     * nem opcionalidade, ou seja, não fica claro se a dependência é fundamental para a construção do
     * objeto ou não.
     *
     * Além disso, não é possível alterar a dependência de forma alguma.
     *
     * Pode ser uma opção interessante quando a classe possui uma grande quantidade de dependências,
     * para deixar o código mais limpo.
     */
    @Autowired
    private TodoValidator validator;

    public void utilizar() {
        var todo = new TodoEntity();
        validator.validarDescricao(todo);
    }
    //-------------------------------------------------------------------------------------
    /**
     * Opção 2: Injeção via setter (aqui também é preciso usar @Autowired). Esta é a forma menos
     * comum, mas não está errada, o Spring aceita esta abordagem também.
     *
     * Esta opção dá uma ideia de opcionalidade, ou seja, essa dependência não seria obrigatória
     * para a construção do objeto, seria injetada apenas quando necessário. Além disso, essa abordagem
     * permite alterar a dependência injetada em runtime (durante a execução, através de lógicas).
     */
    @Autowired
    public void setValidator(TodoValidator validator) {
        this.validator = validator;
    }
    //-------------------------------------------------------------------------------------
    /**
     * Opção 3: Injeção via construtor (a anotação @Autowired é opcional neste caso, se a gente não
     * colocar o Spring já vai automaticamente injetar a dependência via construtor)
     *
     * Esta é a opção mais recomendada pelo Spring, porque deixa a clara a ideia de obrigatoriedade
     * (ou seja, para eu poder criar um BeanGeranciado eu preciso de um TodoValidator). Dessa forma,
     * essa abordagem é recomendada quando a dependência é fundamental para a construção do objeto.
     *
     * Além disso, utilizar injeção via construtor vai facilitar depois a construção de testes
     * automatizados, pois poderemos mockar mais facilmente as dependências da classe.
     */
    @Autowired
    public BeanGerenciado(TodoValidator validator) {
        this.validator = validator;
    }

    //-------------------------------------------------------------------------------------
    /**
     * A classe AppProperties possui os valores das propriedades que estão dentro de app.config do application.yml
     */
    @Autowired
    private AppProperties properties;

    public void imprimirVariaveis() {
        System.out.println(properties.getVariavel());
        System.out.println(properties.getValor1());
    }

}
