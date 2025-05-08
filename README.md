# Backend-Andino

## 🎓 Projeto Acadêmico – Uninove

O **Backend-Andino** é o backend de um sistema completo desenvolvido como parte do projeto acadêmico do curso de **Ciência da Computação** da **Universidade Nove de Julho (Uninove)**. Este repositório representa a lógica de negócio e a API RESTful responsável pelo gerenciamento dos dados da aplicação, em integração com o frontend disponível em [front-andino](https://github.com/Braanco/front-andino.git).

## 🧠 Objetivo

Construir uma aplicação backend robusta e escalável utilizando **Spring Boot**, com foco em boas práticas, arquitetura limpa e integração com um frontend independente, consumindo e manipulando dados por meio de uma API.

## 🔗 Integração com Frontend

Este backend é consumido pelo projeto:

➡️ [Front-Andino (Repositório)](https://github.com/Braanco/front-andino.git)

## 🧪 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3.3.3  
- Spring Web  
- Spring Data JPA  
- Maven  
- Banco de Dados Relacional (MySQL ou PostgreSQL)  
- Swagger (Documentação da API)  
- CORS (habilitado para comunicação com o frontend)  

## 📁 Estrutura do Projeto
```
backend-andino/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/example/backendandino/
│ │ └── resources/
│ │ └── application.properties
├── pom.xml
```

## 🛡️ Resolvendo Problemas de CORS

Caso você enfrente problemas de CORS ao rodar o backend (Spring Boot) e o frontend, siga os passos abaixo:

### Requisitos

- Backend rodando na porta `8080`.
- Frontend consumindo a API em `http://localhost:8080`.

### Passo a Passo para Configuração de CORS

Abra a classe de configuração do CORS (ou crie uma que implemente `WebMvcConfigurer`) e adicione o seguinte método:

```java
@Override
public void addCorsMappings(CorsRegistry registry) {
 registry.addMapping("/**") // Permite todos os caminhos
         .allowedOrigins("http://localhost:3000") // Altere para a URL e porta do seu frontend
         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
         .allowCredentials(true);
}

