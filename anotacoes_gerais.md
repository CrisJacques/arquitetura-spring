# Anotações gerais - arquitetura do Spring

## Customização da inicialização da aplicação

Outra forma de inicializar a aplicação (no lugar de `SpringApplication.run(ProdutosApiApplication.class, args);`) é usar o `SpringApplicationBuilder`, que permite uma série de configurações adicionais:

```java
SpringApplicationBuilder builder = new SpringApplicationBuilder(ProdutosApiApplication.class);

// Fazendo dessa forma, é possível fazer algumas customizações na inicialização, por exemplo:
builder.bannerMode(Banner.Mode.OFF) // desligando o banner do Spring na inicialização

// Além disso, é possível selecionar o profile (ou seja, o ambiente) em que a aplicação irá rodar, por exemplo:
builder.profiles("producao");

// E no final, é só mandar rodar
builder.run(args);

// Depois que a aplicação inicializa também é possível fazer outras ações, por exemplo obter um Bean a partir
// do ApplicationContext
ConfigurableApplicationContext applicationContext = builder.context();
var produtoRepository = applicationContext.getBean("produtoRepository");
```

## Inicialização Lazy dos Beans

- Quando a aplicação sobe, todos os Beans já são instanciados por padrão e registrados no container de injeção de
  dependências.
- Caso seja desejado que os Beans sejam instanciados apenas quando necessário, utilizamos a anotação `@Lazy`, logo antes
  da anotação `@Component` (ou das anotações especializadas como `@Service` por exemplo).
- O padrão é que a inicialização dos Beans não seja Lazy, o recomendado é usar o `@Lazy` apenas em casos mais
  específicos.
- Também é possível configurar para que todos os Beans tenham inicialização Lazy, sem precisar colocar `@Lazy` em todos
  eles. Isso pode agilizar a inicialização da aplicação, mas se ocorrer erro na instanciação de qualquer Bean, isso só
  será descoberto em runtime. 
- Para fazer esta configuração, é possível usar o `SpringApplicationBuilder`, no `main` da aplicação:
```java
SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
builder.lazyInicialization(true);
```
- Outra forma é configurar isso no `application.properties`:
```java 
spring.main.lazy-initialization=true
```

### Observações
- Caso seja desejado que a maioria dos Beans tenha inicialização Lazy, é recomendado configurar o padrão como Lazy, 
conforme mostrado acima, e nas classes que não devem ser Lazy colocar `@Lazy(false)`;
- Caso seja desejado que a maioria dos Beans não tenha inicialização Lazy (o padrão do Spring), não é preciso fazer 
nenhuma configuração adicional, basta colocar `@Lazy(true)` nos Beans em que se deseja inicialização Lazy.


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

### Observações

- O escopo `request` não guarda estado, mas os escopos `session` e `application` guardam.
- Não é recomendado guardar estado em Beans ou Components com escopo `singleton`.
- Para evitar erros de digitação, use constantes ao invés de digitar os nomes dos escopos:

  | Escopo | Constante |
  | --- | --- |
  | `singleton` | `BeanDefinition.SCOPE_SINGLETON` |
  | `request` | `WebApplicationContext.SCOPE_REQUEST` |
  | `session` | `WebApplicationContext.SCOPE_SESSION` |
  | `application` | `WebApplicationContext.SCOPE_APPLICATION` |

## Arquivo application.properties

- Neste arquivo, é possível externalizar várias configurações da aplicação, sem precisar alterá-las programaticamente.
- Além das propriedades do Spring e dos seus starters, é possível criar propriedades customizadas para uso pela 
aplicação, por exemplo:
```java
app.config.variavel=X
```
- Exemplos de starters do Spring (ver `pom.xml`):
  - spring-boot-starter-parent
  - spring-boot-starter-data-jpa
  - spring-boot-starter-webmvc
- Na documentação do Spring (docs.spring.io) são mostradas todas as configurações possíveis, na seção Common Application
Properties.
- Exemplo de arquivo `application.properties`:
```java
spring.application.name=arquiteturaspring
spring.jpa.show_sql=true
spring.main.lazy-initialization=false
app.config.variavel=X
```

## Arquivo application.yml

- Tem a mesma finalidade do `application.properties`, mas possui uma sintaxe mais moderna, fácil de ler e é a versão
mais usada nas empresas atualmente. Possui estrutura hierárquica de propriedades, facilitando o entendimento.
- Exemplo de arquivo `application.yml`:

```java
spring:
  application:
    name: arquitetura-spring
  jpa:
    show_sql: true
  main:
    lazy-initialization: true

app:
  config:
    variavel: X
```
- **É preciso escolher entre um arquivo e outro, não pode ter os dois arquivos no projeto.**  
