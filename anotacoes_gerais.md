# Anotações gerais - arquitetura do Spring

## Componentes do Spring

`@RestController`, `@Service` e `@Repository` também são componentes, mas são anotações de especialização.

Quando precisamos registrar um componente que não é `@RestController`, `@Service` nem `@Repository`, usamos a anotação genérica `@Component`.

## Escopo dos Beans gerenciados

Por padrão, o escopo dos Beans gerenciados pelo Spring é `singleton`: é criada uma única instância dos objetos, que atende toda a aplicação.

Nesse caso, não é necessário usar a anotação `@Scope("singleton")`, pois esse já é o comportamento padrão.

A anotação `@Scope` pode ser usada tanto em Components quanto em Beans.

### Outras opções de escopo

Os exemplos abaixo são voltados a aplicações web e não representam todas as opções disponíveis na documentação do Spring.

- **`request`**: o ciclo de vida do Bean existe apenas durante uma requisição. Ele é instanciado quando a requisição ocorre e deixa de existir após o término dela. Portanto, há uma instância para cada requisição.
- **`session`**: o Bean existe enquanto durar a sessão do usuário. Esse escopo é útil para guardar dados do usuário logado naquele momento, mantendo-os associados ao usuário.
- **`application`**: semelhante ao `session`, mas válido para todos os usuários. Não é um `singleton`, pois o `singleton` é utilizado em qualquer tipo de projeto, enquanto o escopo `application` é específico para aplicações web.

## Observações

- O escopo `request` não guarda estado, mas os escopos `session` e `application` guardam.
- Não é recomendado guardar estado em Beans ou Components com escopo `singleton`.
- Para evitar erros de digitação, use constantes ao invés de digitar os nomes dos escopos:

  | Escopo | Constante |
  | --- | --- |
  | `singleton` | `BeanDefinition.SCOPE_SINGLETON` |
  | `request` | `WebApplicationContext.SCOPE_REQUEST` |
  | `session` | `WebApplicationContext.SCOPE_SESSION` |
  | `application` | `WebApplicationContext.SCOPE_APPLICATION` |