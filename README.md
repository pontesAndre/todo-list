# TODO-LIST API
API para gerenciar tarefas.
TODO-LIST é uma API REST com Spring Boot implementada em JAVA11. Utiliza:
- Spring Boot Data JPA, e persistência em memória com banco de dados H2.
- Spring Boot Actuator para monitorar: status, memória, processamento, JVM, requisições web, entre outras métricas;
- Autenticação stateless utilizando tokens JWT (Json Web Token);
- Cache do Spring Boot para melhorar a performance;
- Documentação utilizar o Swagger, com o módulo SpringFox Swagger;
 
## Requisitos para executar a API

Para executar o projeto, será necessário instalar:

- [JDK 11: Necessário para executar o projeto Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Maven 3.6.1: Necessário para realizar o build do projeto Java](https://maven.apache.org/download.cgi)
- [Eclipse: Para desenvolvimento do projeto](https://www.eclipse.org/downloads/packages/release/2019-06/r/eclipse-ide-enterprise-java-developers)

## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub em um diretório de sua preferência:

```shell
cd <diretorio_projeto>
git clone https://github.com/pontesAndre/todo-list.git
```
## Build 

##### command line
Para o build  do projeto com o Maven, executar os comandos abaixo :

```shell
cd <diretorio_projeto>/todo-list
mvn clean install
```
O comando irá baixar todas as dependências do projeto.

##### IDE Eclipse
-  Importar o projeto na IDE Eclipse (Existing Maven Project)
-  Execute RunAs Mavem Install para baixar dependências necessárias.

## Executar 

##### command line

```shell
cd <diretorio_projeto>/todo-list
mvn clean install
java -jar <diretorio_projeto>/todo-list/target/todo-list-0.0.1-SNAPSHOT.jar
```
##### IDE Eclipse

- Executar a classe TodoListApplication que possui a anotação @springbootapplication, e tem o método main, que invoca o método run da classe SpringApplication. 

## Documentação

Para acessar a documentação da API, devemos executar o projeto e entrar no endereço: 
  -  http://localhost:8080/swagger-ui.html;


## Métricas e desempenho

Para acessar as informações disponibilizadas pelo Actuator, devemos executar o projeto e entrar no endereço:
   -  http://localhost:8080/actuator; 

##### Exemplos

- Para que o Actuator acesse:  /metrics

```shell
curl -X GET "http://localhost:8080/actuator/metrics" -H "accept: */*""
```
- Para que o Actuator acesse: /health

```shell
curl -X GET "http://localhost:8080/actuator/health" -H "accept: */*""
```
- Para que o Actuator exponha mais informações sobre a API

```shell
curl -X GET "localhost:8080/actuator" -H "accept: */*"
```

## Autenticação

- A API utiliza autenticação stateless utilizando tokens JWT (Json Web Token).
- Autenticação utiliza email e senha. A API usa a senha no formato do BCrypt.
- Na versão atual temos um usuário padrão.

```json
   {
	"email":"admin@email.com",
	"password":"1234"
	}
```

- Geração do token para acessar endpoint que necessita de autenticação :

```shell
curl -X POST "http://localhost:8080/auth" -H "accept: */*" -H "Content-Type: application/json" -d "{\t\"email\":\"admin@email.com\",\t\"password\":\"1234\"\t}"
```

	
## Exemplos

- lista 

```shell
curl -X GET "http://localhost:8080/todo" -H "accept: */*"
```

- detalhe

```shell
curl -X GET "http://localhost:8080/todo/1" -H "accept: */*"
 ```
 
- exclusão 

```shell
curl -X DELETE "http://localhost:8080/todo/2" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgVE9ETy1MSVNUIiwic3ViIjoiMSIsImlhdCI6MTU2NzU5MDY1NSwiZXhwIjoxNTY3NTkwODcxfQ.2o7iIdZM3Ms07_RvHD4AjKL7W9ITxjJ-MhoU5KcNAfQ"
```
- atualização 

```shell
curl -X PUT "http://localhost:8080/todo/1" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgVE9ETy1MSVNUIiwic3ViIjoiMSIsImlhdCI6MTU2NzU5MTM5MCwiZXhwIjoxNTY3NTk4NTkwfQ.A1FrpBG6vC_QDs0DYaMwjISJUMpBk-IrlnhEmAjKJ1c" -H "Content-Type: application/json" -d "{ \"description\": \"descricao tarefa\", \"summary\": \"titulo\"}"
```

## Persistência
- A API usa persistência em memória com banco de dados H2
- o Spring Boot popula automaticamente o banco de dados da aplicação. Baseado no arquivo src/main/resources/data.sql 

