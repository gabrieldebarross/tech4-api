# E-commerce Backend

Este é o backend de uma aplicação de e-commerce, responsável por gerenciar os dados dos usuários, incluindo operações de criação, atualização, listagem, exclusão e autenticação de usuários.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para criação de aplicações Java baseadas em Spring, com foco em produtividade e simplicidade.
- **Spring Data JPA**: Utilizado para interagir com o banco de dados de forma simplificada, permitindo realizar operações CRUD (Create, Read, Update, Delete) de forma automática.
- **Spring Security**: Framework de segurança utilizado para criptografar as senhas dos usuários utilizando o algoritmo **BCrypt**.
- **Lombok**: Biblioteca que reduz o código boilerplate, como getters, setters e construtores.
- **H2 Database** (opcional dependendo da configuração): Banco de dados em memória utilizado para fins de desenvolvimento.
- **Jakarta Validation**: Usado para validação de dados de entrada, como as senhas e o nome de usuário.
- **Spring Web**: Para criação da API RESTful.
- **Swagger** (opcional): Para documentar e testar a API.

## Funcionalidades da API

A API oferece diversos endpoints para gerenciar os usuários do sistema. Estes endpoints são responsáveis por operações como criação, leitura, atualização, exclusão e autenticação de usuários.

### 1. **Listar Usuários**
- **GET /user**  
  Retorna uma lista de todos os usuários cadastrados no sistema.

### 2. **Criar Novo Usuário**
- **POST /user**  
  Cria um novo usuário. A senha do usuário é criptografada antes de ser salva no banco de dados.

### 3. **Editar Usuário**
- **PUT /user**  
  Edita os dados de um usuário existente. A senha do usuário também é criptografada antes de ser salva.

### 4. **Deletar Usuário**
- **DELETE /user/{id}**  
  Exclui um usuário baseado no seu ID.

### 5. **Autenticar Usuário (Login)**
- **POST /user/login**  
  Valida a senha fornecida para o usuário. A senha é comparada com a versão criptografada armazenada no banco de dados.

## Estrutura do Código

### 1. **Model**
A classe `UserModel` representa os dados de um usuário. Ela utiliza as anotações de validação do Jakarta para garantir que os dados de entrada sejam válidos.

### 2. **Repositório**
A interface `UserInterface` estende `JpaRepository`, o que proporciona uma maneira simples de realizar operações no banco de dados sem precisar escrever SQL manualmente.

### 3. **Serviço**
A classe `UserService` contém a lógica de negócio para manipular os usuários. Ela interage com o repositório para executar operações CRUD e também utiliza o `BCryptPasswordEncoder` para criptografar as senhas dos usuários.

### 4. **Controlador**
O controlador `UserController` mapeia as requisições HTTP para os métodos do serviço. Ele também lida com validações de entrada e erros utilizando a anotação `@ExceptionHandler`.

### 5. **Segurança**
As senhas dos usuários são criptografadas antes de serem armazenadas no banco de dados, utilizando o `BCryptPasswordEncoder`. Isso garante que as senhas não sejam armazenadas em texto claro, aumentando a segurança do sistema.

### 6. **Tratamento de Erros**
A classe `UserController` também inclui um método que lida com exceções de validação. Caso algum dado de entrada seja inválido, o erro é capturado e retornado de forma estruturada ao cliente.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/gabrieldebarross/ecommerce-backend.git

2. Navegue até o diretório do projeto:

    ```bash
    cd ecommerce-backend

3. Compile e execute a aplicação:

    ```bash
    ./mvnw spring-boot:run