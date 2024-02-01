# Spring Boot RESTful Web Services

Este é um projeto simples do Spring Boot que demonstra como criar um serviço web RESTful usando Java e o Spring Framework. O projeto consiste em uma única classe de controlador que expõe 5 pontos de extremidade: um para criar um novo usuário, um para recuperar um usuário por ID,um para atualizar usuario, um para obter todos os usuarios e outro para deletar um usuario por ID.

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Como usar

1. Clone este repositório:

```bash
git clone https://github.com/JamesonHenrique/springboot-restful-webservices.git
```

2. Abra o projeto em seu IDE

3. Compile e execute o projeto

4. Use um cliente REST como o Postman para testar os pontos de extremidade

## Pontos de extremidade

### Criando um novo usuário

POST http://localhost:8080/api/users

Corpo da solicitação:

```json
{
  "name": "John Doe",
  "email": "johndoe@example.com"
}
```

Resposta:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}
```

### Recuperando um usuário por ID

GET http://localhost:8080/api/users/1

Resposta:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}
```
### Recuperando todos os usuarios

GET http://localhost:8080/api/users

Resposta:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}
 {
  "id": 2,
  "firstName": "Pedro",
  "lastName": "Henrique",
  "email": "pedrohenrique@example.com"
    }
```
### Atualizando usuario

PUT http://localhost:8080/api/users

Corpo da solicitação:

```json
{
  "name": "John Husbon",
  "email": "johnhusbon@example.com"
}
```

Resposta:

```json
{
  "id": 1,
  "name": "John Husbon",
  "email": "johnhusbon@example.com"
}
```
### Deletando usuarios

DELETE http://localhost:8080/api/users

Resposta:

```json
User Deleted


