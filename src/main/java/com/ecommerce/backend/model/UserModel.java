package com.ecommerce.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Lombok, cria getter, setters, contrutores etc

@Entity // Indica que a classe UserModel é uma entidade JPA, ela será mapeada para uma tabela no banco de dados
@Table(name= "user") // Especifica o nome da tabela no banco de dados, que sera "user". Mapeia a classe para uma tabela no banco de dados.
// Representa um usuário no banco de dados
public class UserModel {

    @Id // Marca o campo Id como a chave primaria da tabela user no banco de dados
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Indica que o valor de id será gerado automaticamente pelo banco de dados. 
    @Column(name= "id") // Especifica que este campo será mapeado para a coluna id na tabela do banco de dados
    private Integer id;

    @NotBlank(message= "O usuário é obrigatório!") // Anotação do JAKARTA VALIDATION garante que o campo username não seja vazio ou apenas espaços em branco. Caso contrário, a mensagem de erro personalizada será retornada
    @Size(min= 5, message= "O nome deve ter no mínimo 5 caracteres!") // Anotação do JAKARTA VALIDATION que garante que o tamanho do campo username tenha pelo menos 5 caracteres. Caso contrário, a mensagem de erro será retornada
    @Column(name= "username", length= 255, nullable= false) // Especifica que este campo será mapeado para a coluna username na tabela do banco de dados
    private String username;

    @Size(min= 5, message= "O nome deve ter no mínimo 5 caracteres!") // Anotação do JAKARTA VALIDATION que garante que o tamanho do campo username tenha pelo menos 5 caracteres. Caso contrário, a mensagem de erro será retornada
    @NotBlank(message= "A senha é obrigatória!") // // Anotação do JAKARTA VALIDATION garante que o campo username não seja vazio ou apenas espaços em branco. Caso contrário, a mensagem de erro personalizada será retornada
    @Column(name= "password", columnDefinition= "TEXT", nullable= false) // Especifica que este campo será mapeado para a coluna password na tabela do banco de dados
    private String password;
}
