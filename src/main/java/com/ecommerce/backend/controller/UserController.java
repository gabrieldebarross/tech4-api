package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.model.UserModel;
import com.ecommerce.backend.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> ListaUsuario() {
        return ResponseEntity.status(200).body(userService.listarUsuario());
    }

    @PostMapping
    public ResponseEntity<UserModel> criarUsuario(@RequestBody UserModel usuario){
        return ResponseEntity.status(201).body(userService.criarUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<UserModel> editarUsuario(@RequestBody UserModel usuario){
        return ResponseEntity.status(201).body(userService.editarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario (@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.status(204).build();
    }
}
