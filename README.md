# backend-andino
 Esse é o backend do projeto da uninove

## Resolvendo Problemas de CORS

Caso você enfrente problemas de CORS ao rodar o backend (Spring Boot) e o frontend, siga os passos abaixo:

### Requisitos

- **Backend rodando na porta 8080**: Certifique-se de que o backend esteja rodando localmente na porta 8080 para evitar conflitos ao fazer requisições do frontend.

### Passo a Passo para Configuração de CORS

1. **Abra a classe existente de configuração CORS**.

2. **Adicione ou atualize o método `addCorsMappings`**:

   ```java
   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**") // Permite todos os caminhos
               .allowedOrigins("http://localhost:3000") // Altere para a URL e porta do seu frontend
               .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
               .allowCredentials(true);
   }


