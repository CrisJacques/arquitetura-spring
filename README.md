# Arquitetura Spring

> **Projeto em construção.** Esta aplicação é um laboratório de exemplos que demonstra diferentes aspectos da arquitetura e dos recursos do Spring Framework.

O objetivo é estudar conceitos de forma prática por meio de exemplos didáticos e independentes, incluindo injeção de dependências, registro e resolução de beans, arquitetura em camadas, persistência com JPA e criação de APIs REST.

## Tecnologias

- **Java 25**
- **Spring Boot 4.1.2**
- **Spring Web MVC**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (banco em memória)
- **Maven**
- **JUnit 5**

## Exemplos disponíveis

### Montadora e resolução de dependências

O pacote `montadora` demonstra como o Spring gerencia beans e resolve dependências quando existem várias implementações do mesmo tipo:

- Bean padrão com `@Primary`
- Seleção explícita com `@Qualifier`
- Qualifier personalizado por meio de anotações customizadas
- Registro de beans com `@Configuration` e `@Bean`
- Uso de componentes e injeção de dependências

### API REST de tarefas

O módulo `todos` demonstra uma API REST organizada em camadas:

- `Controller`: exposição dos endpoints HTTP
- `Service`: regras de negócio e orquestração
- `Repository`: acesso aos dados com Spring Data JPA
- `Entity`: representação da tabela no banco
- `ControllerAdvice`: tratamento centralizado de exceções
- Validações e envio de mensagens por componentes separados

## Pré-requisitos

- JDK 25
- Maven 3.9 ou superior, ou o Maven Wrapper incluso no projeto

Verifique as versões instaladas:

```bash
java -version
mvn -version
```

## Como executar

Na raiz do projeto, execute:

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux ou macOS

```bash
./mvnw spring-boot:run
```

Também é possível executar a classe `ArquiteturaSpringApplication` diretamente pela IDE.

Por padrão, a aplicação inicia em `http://localhost:8080`.

## Banco de dados H2

O projeto utiliza o banco H2 em memória. Os dados são criados ao iniciar a aplicação e perdidos ao finalizá-la.

- Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: vazia

## Endpoints

### Montadora

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/carros/aspirado` | Usa o bean marcado como `@Primary` |
| `POST` | `/carros/eletrico` | Usa o bean selecionado por `@Qualifier` |
| `POST` | `/carros/turbo` | Usa o bean selecionado pela anotação `@Turbo` |

Exemplo de corpo da requisição:

```json
{
  "montadora": "HONDA",
  "tipo": "inteligente"
}
```

Os valores possíveis de `montadora` devem corresponder ao enum `Montadora` utilizado pelo projeto.

### Tarefas

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/todos` | Cria uma tarefa |
| `GET` | `/todos` | Lista todas as tarefas |
| `GET` | `/todos/{id}` | Busca uma tarefa pelo identificador |
| `PUT` | `/todos/{id}` | Atualiza uma tarefa |
| `DELETE` | `/todos/{id}` | Remove uma tarefa |

Exemplo de corpo para criação:

```json
{
  "descricao": "Estudar arquitetura Spring",
  "concluido": false
}
```

Exemplo de criação no terminal:

```bash
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d "{\"descricao\":\"Estudar arquitetura Spring\",\"concluido\":false}"
```

## Estrutura principal

```text
src/
├── main/
│   ├── java/com/example/arquiteturaspring/
│   │   ├── ArquiteturaSpringApplication.java
│   │   ├── montadora/       # Beans, qualifiers e exemplos de injeção
│   │   └── todos/           # API REST em arquitetura em camadas
│   └── resources/
│       └── application.yaml
└── test/
    └── java/com/example/arquiteturaspring/
```

## Testes

Execute a suíte de testes com:

```bash
./mvnw test
```

No Windows:

```powershell
.\mvnw.cmd test
```

## Status do projeto

Este repositório está em desenvolvimento ativo. Novos exemplos e melhorias poderão incluir:

- Mais cenários de injeção de dependências
- Profiles e configurações por ambiente
- Validações com Bean Validation
- Testes de integração e testes de API
- Documentação interativa de endpoints
- Exemplos adicionais de arquitetura e organização de aplicações Spring
