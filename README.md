# TaskManager

Projeto desenvolvido para estudo de **Java com Spring Boot**, com foco na criação de uma API REST simples para gerenciamento de tarefas.

A aplicação permite criar, listar, buscar, atualizar e remover tarefas, utilizando uma estrutura organizada em camadas e conceitos importantes do desenvolvimento backend, como separação de responsabilidades, casos de uso, validação de entrada, tratamento de exceções, repositório em memória e testes automatizados.

---

## Sobre o projeto

O **TaskManager** é uma API REST para controle de tarefas. Cada tarefa possui um identificador único, título, descrição opcional e status.

O projeto foi construído com objetivo educacional, praticando conceitos fundamentais de aplicações backend com Spring Boot, como:

- criação de controllers REST;
- uso de DTOs para entrada e saída de dados;
- validação de requisições;
- organização por camadas;
- criação de regras de negócio em casos de uso;
- uso de repository como abstração de persistência;
- tratamento global de exceções;
- testes com Spring Boot e MockMvc.

---

## Arquitetura inspirada em DDD

O projeto foi estruturado com uma organização inspirada em conceitos de **DDD (Domain-Driven Design)** e **Clean Architecture**, separando o domínio da aplicação dos detalhes de infraestrutura.

A camada `domain` concentra os principais conceitos de negócio, como `Task`, `TaskId`, `TaskStatus` e a abstração `TaskRepository`.

A camada `application` contém os casos de uso da aplicação, responsáveis por orquestrar as operações principais, como criação, listagem, busca, atualização e remoção de tarefas.

A camada `infrastructure` contém os detalhes externos da aplicação, como controllers REST, DTOs HTTP, tratamento de exceções e a implementação concreta do repositório em memória.

Essa separação facilita a manutenção do código, melhora a organização do projeto e permite que detalhes técnicos, como HTTP ou persistência, não fiquem acoplados diretamente ao núcleo da regra de negócio.

> Observação: por ser um projeto de estudo e de escopo reduzido, a aplicação não busca implementar DDD de forma completa, mas sim aplicar uma estrutura inspirada em seus princípios, principalmente na separação entre domínio, aplicação e infraestrutura.

---

## Funcionalidades

A API permite realizar as seguintes operações:

- Criar uma nova tarefa;
- Listar todas as tarefas cadastradas;
- Buscar uma tarefa pelo ID;
- Atualizar dados de uma tarefa;
- Remover uma tarefa;
- Validar dados enviados na requisição;
- Retornar erro quando uma tarefa não é encontrada.

---

## Modelo de domínio

A entidade principal do projeto é a `Task`.

Ela representa uma tarefa dentro do sistema e possui os seguintes dados:

| Campo | Descrição |
|---|---|
| `id` | Identificador único da tarefa |
| `title` | Título da tarefa |
| `description` | Descrição opcional da tarefa |
| `status` | Situação atual da tarefa |

Os status disponíveis são:

| Status | Descrição |
|---|---|
| `PENDING` | Tarefa pendente |
| `IN_PROGRESS` | Tarefa em andamento |
| `COMPLETED` | Tarefa concluída |

Ao criar uma nova tarefa, o sistema gera automaticamente um identificador único e define o status inicial como `PENDING`.

---

## Estrutura do projeto

O projeto foi organizado buscando separar as responsabilidades da aplicação em camadas.

```text
src/main/java/com/dio/taskmanager
├── application
│   ├── input
│   ├── output
│   ├── CreateTaskUseCase.java
│   ├── DeleteTaskUseCase.java
│   ├── GetTaskByIdUseCase.java
│   ├── GetTasksUseCase.java
│   └── UpdateTaskUseCase.java
│
├── domain
│   ├── Task.java
│   ├── TaskId.java
│   ├── TaskNotFoundException.java
│   ├── TaskRepository.java
│   └── TaskStatus.java
│
├── infrastructure
│   ├── http
│   │   ├── request
│   │   ├── response
│   │   ├── GlobalExceptionHandler.java
│   │   └── TaskController.java
│   │
│   └── repository
│       └── InMemoryTaskRepository.java
│
└── TaskmanagerApplication.java
```

### Camada `domain`

Contém as regras e modelos principais do sistema.

Nessa camada ficam:

- `Task`;
- `TaskId`;
- `TaskStatus`;
- `TaskRepository`;
- `TaskNotFoundException`.

A ideia é manter o núcleo da aplicação separado dos detalhes de infraestrutura, como HTTP ou persistência.

### Camada `application`

Contém os casos de uso da aplicação.

Cada operação principal foi separada em uma classe própria:

- `CreateTaskUseCase`;
- `GetTasksUseCase`;
- `GetTaskByIdUseCase`;
- `UpdateTaskUseCase`;
- `DeleteTaskUseCase`.

Essa organização deixa o controller mais simples e concentra a lógica da aplicação em classes específicas.

### Camada `infrastructure`

Contém os detalhes externos da aplicação, como:

- controllers REST;
- DTOs de requisição;
- DTOs de resposta;
- tratamento de exceções;
- implementação concreta do repositório.

Atualmente, o projeto utiliza um repositório em memória, ou seja, os dados são mantidos durante a execução da aplicação e são perdidos quando ela é reiniciada.

---

## Endpoints da API

A URL base dos endpoints é:

```text
/tasks
```

---

### Criar tarefa

```http
POST /tasks
```

Exemplo de requisição:

```json
{
  "title": "Estudar Spring Boot",
  "description": "Praticar criação de APIs REST com Java"
}
```

Exemplo de resposta:

```json
{
  "id": "f4e8e1b0-0b0b-4d4c-8f0f-8b0b0b0b0b0b",
  "title": "Estudar Spring Boot",
  "description": "Praticar criação de APIs REST com Java",
  "status": "PENDING"
}
```

Status HTTP esperado:

```text
201 Created
```

---

### Listar tarefas

```http
GET /tasks
```

Exemplo de resposta:

```json
[
  {
    "id": "f4e8e1b0-0b0b-4d4c-8f0f-8b0b0b0b0b0b",
    "title": "Estudar Spring Boot",
    "description": "Praticar criação de APIs REST com Java",
    "status": "PENDING"
  }
]
```

---

### Buscar tarefa por ID

```http
GET /tasks/{id}
```

Exemplo:

```http
GET /tasks/f4e8e1b0-0b0b-4d4c-8f0f-8b0b0b0b0b0b
```

Exemplo de resposta:

```json
{
  "id": "f4e8e1b0-0b0b-4d4c-8f0f-8b0b0b0b0b0b",
  "title": "Estudar Spring Boot",
  "description": "Praticar criação de APIs REST com Java",
  "status": "PENDING"
}
```

Caso o ID não seja encontrado, a API retorna erro informando que a tarefa não existe.

---

### Atualizar tarefa

```http
PATCH /tasks/{id}
```

Exemplo de requisição:

```json
{
  "title": "Estudar testes com Spring",
  "description": "Criar testes automatizados com MockMvc",
  "status": "IN_PROGRESS"
}
```

Exemplo de resposta:

```json
{
  "id": "f4e8e1b0-0b0b-4d4c-8f0f-8b0b0b0b0b0b",
  "title": "Estudar testes com Spring",
  "description": "Criar testes automatizados com MockMvc",
  "status": "IN_PROGRESS"
}
```

---

### Remover tarefa

```http
DELETE /tasks/{id}
```

Exemplo:

```http
DELETE /tasks/f4e8e1b0-0b0b-4d4c-8f0f-8b0b0b0b0b0b
```

Status HTTP esperado:

```text
204 No Content
```

---

## Validações

Na criação de tarefas, o projeto aplica validações nos dados recebidos pela API.

Regras principais:

| Campo | Regra |
|---|---|
| `title` | obrigatório |
| `title` | mínimo de 3 caracteres |
| `title` | máximo de 100 caracteres |
| `description` | opcional |
| `description` | máximo de 500 caracteres |

Essas validações ajudam a evitar que tarefas inválidas sejam criadas na aplicação.

---

## Tratamento de exceções

O projeto possui um handler global de exceções, responsável por capturar erros e retornar respostas mais adequadas para a API.

Exemplos de situações tratadas:

- tentativa de buscar uma tarefa inexistente;
- tentativa de atualizar uma tarefa inexistente;
- tentativa de remover uma tarefa inexistente;
- envio de dados inválidos na criação de uma tarefa.

---

## Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Web
- Spring Validation
- Gradle
- Lombok
- JUnit 5
- MockMvc
- Spring REST Docs
- Asciidoctor

---

## Testes

O projeto possui teste automatizado para validar o comportamento da API.

Um dos testes implementados verifica a criação de uma nova tarefa por meio do endpoint `POST /tasks`, conferindo se:

- a API retorna status `201 Created`;
- o ID da tarefa é gerado;
- o título retorna corretamente;
- a descrição retorna corretamente;
- o status inicial é `PENDING`.

Para executar os testes:

```bash
./gradlew test
```

---

## Como executar o projeto

### Pré-requisitos

Antes de iniciar, é necessário ter instalado:

- Java 25 ou superior;
- Git;
- Gradle, ou utilizar o Gradle Wrapper já incluído no projeto.

---

### Clonar o repositório

```bash
git clone https://github.com/klesleySilvaOliveira/TaskManager.git
```

```bash
cd TaskManager
```

---

### Executar a aplicação

No Linux ou macOS:

```bash
./gradlew bootRun
```

No Windows:

```bash
gradlew.bat bootRun
```

A aplicação será iniciada, por padrão, em:

```text
http://localhost:8080
```

---

## Exemplos de uso com cURL

### Criar uma tarefa

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Estudar Java",
    "description": "Revisar conceitos de Spring Boot"
  }'
```

### Listar tarefas

```bash
curl http://localhost:8080/tasks
```

### Buscar tarefa por ID

```bash
curl http://localhost:8080/tasks/{id}
```

### Atualizar tarefa

```bash
curl -X PATCH http://localhost:8080/tasks/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Estudar Java e Spring",
    "description": "Praticar desenvolvimento de APIs REST",
    "status": "IN_PROGRESS"
  }'
```

### Remover tarefa

```bash
curl -X DELETE http://localhost:8080/tasks/{id}
```

---

## Conceitos praticados

Durante o desenvolvimento deste projeto, foram praticados conceitos importantes do ecossistema Java e Spring Boot:

- criação de API REST;
- uso de annotations do Spring;
- injeção de dependências;
- organização inspirada em DDD;
- separação entre domínio, aplicação e infraestrutura;
- uso de casos de uso para representar operações da aplicação;
- uso de repository como abstração de persistência;
- aplicação de validações com Jakarta Validation;
- uso de records para DTOs e value objects;
- uso de enum para controle de status;
- criação de identificador com UUID;
- tratamento global de exceções;
- implementação de repositório em memória;
- testes de endpoint com MockMvc.

---

## Possíveis melhorias futuras

Como este é um projeto de estudo, algumas melhorias podem ser implementadas futuramente:

- adicionar banco de dados com Spring Data JPA;
- criar migrations com Flyway ou Liquibase;
- melhorar os retornos de erro da API;
- adicionar testes para listagem, busca, atualização e remoção;
- gerar documentação automática com Spring REST Docs;
- criar uma collection do Postman ou Insomnia;
- adicionar Docker;
- criar autenticação e autorização;
- melhorar a estrutura dos DTOs de atualização;
- adicionar paginação e filtros na listagem de tarefas.

---

## Autor

Desenvolvido por **Klesley Oliveira** como parte dos estudos em Java, Spring Boot e desenvolvimento backend.
