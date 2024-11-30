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

@Entity
@Table(name= "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @NotBlank(message= "O usuário é obrigatório!")
    @Size(min= 5, message= "O nome deve ter no mínimo 5 caracteres!")
    @Column(name= "username", length= 255, nullable= false)
    private String username;

    @Size(min= 5, message= "O nome deve ter no mínimo 5 caracteres!")
    @NotBlank(message= "A senha é obrigatória!")
    @Column(name= "password", columnDefinition= "TEXT", nullable= false)
    private String password;
}
