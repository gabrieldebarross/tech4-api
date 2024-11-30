package com.ecommerce.backend.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.UserModel;
import com.ecommerce.backend.repository.UserInterface;

@Service
public class UserService {
    private final UserInterface repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserInterface repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserModel> listarUsuario(){
        List<UserModel> lista = repository.findAll();
        return lista;
    }

    public UserModel criarUsuario(UserModel usuario){
        String encoder = this.passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encoder);
        UserModel novoUsuario = repository.save(usuario);
        return novoUsuario;
    }

    public UserModel editarUsuario(UserModel usuario){
        String encoder = this.passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encoder);
        UserModel novoUsuario = repository.save(usuario);
        return novoUsuario;
    }

    public Boolean deletarUsuario(Long id){
        repository.deleteById(id);
        return true;
    }

    public Boolean validarSenha(UserModel usuario) {
        // Se o ID do usuário for Integer e o método getById espera Long
        Long usuarioId = Long.valueOf(usuario.getId()); // Converte Integer para Long
        
        // Obtém a senha criptografada do banco de dados
        @SuppressWarnings("deprecation")
        String senhaCriptografada = repository.getById(usuarioId).getPassword();
        
        // Verifica se a senha fornecida corresponde à senha criptografada
        Boolean valid = passwordEncoder.matches(usuario.getPassword(), senhaCriptografada);
        
        return valid;
    }
    
    
}
