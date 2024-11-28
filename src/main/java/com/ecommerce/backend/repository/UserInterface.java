package com.ecommerce.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.model.UserModel;

public interface UserInterface extends JpaRepository<UserModel, Long>{

}