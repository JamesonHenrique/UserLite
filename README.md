# User management

Este projeto visa oferecer um sistema de gerenciamento de usuários simples usando o Spring Boot e o Hibernate.
## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Como usar

1. Clone este repositório:

```bash
git clone https://github.com/JamesonHenrique/User-Management.git
```

2. Abra o projeto em seu IDE
   
4. Altere o usuario e senha para que o projeto possa acessar o banco mysql.

      Vá até /src/main/resources/application.properties;

      Altere as propriedades informado o usuário e senha do seu banco de dados:

      spring.datasource.username=usuario

      spring.datasource.password=usuario

4. Compile e execute o projeto

5. Use o Swagger de preferencia para testar a api ou o que você preferir
   
      http://localhost:8080/swagger-ui/index.html

## Pontos de extremidade

### Criando um novo usuário

POST http://localhost:8080/api/users

Corpo da solicitação:

```json
{ 
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
```

Resposta:

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
```

### Recuperando um usuário por ID

GET http://localhost:8080/api/users/1

Resposta:

```json
{

  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
```
### Recuperando todos os usuários

GET http://localhost:8080/api/users

Resposta:

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
 {
  "id": 2,
  "firstName": "Pedro",
  "lastName": "Henrique",
  "email": "pedrohenrique@example.com"
    }
```
### Atualizando usuários

PUT http://localhost:8080/api/users/1

Corpo da solicitação:

```json
{
  
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
```

Resposta:

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
```
### Deletando usuários

DELETE http://localhost:8080/api/users

Resposta:

```json
User successfully deleted!


