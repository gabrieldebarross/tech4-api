package com.ecommerce.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.model.UserModel;

// UserInterface trabalha com objs da classe UserModel, usuários no bd.
public interface UserInterface extends JpaRepository<UserModel, Long>{
    /*  Interface pública, que extende (herda) da interface JpaRepository, significa que UserInterface se torna 
    um repositório completo para a entidade UserModel.

    JpaRepository é uma interface fornecida pelo Spring Data JPA, que já contem metodos comuns para manipulacao de dados em um banco de dados relacional. Como Salvar, buscar, excluir, listar todos e contar.
    */

    // A interface UserInterface permite que o Spring Data JPA forneça automaticamente a implementação de métodos CRUD para a classe UserModel
}