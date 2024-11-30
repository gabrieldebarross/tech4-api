package com.ecommerce.backend.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.model.UserModel;
import com.ecommerce.backend.repository.UserInterface;

@Service // Componente de servico do Spring, responsavel pela logica de negocio da aplicacao
public class UserService {

    private final UserInterface repository; // O repositorio UserInterface é injeado no servico para permitir que o UserService acesse o banco de dados para realizar CRUDS.
    private final PasswordEncoder passwordEncoder; // Instancia de PassowordEncoder, usada para criptografar as senhas dos usuarios

    public UserService(UserInterface repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Método para listar todos os usuarios do bd
    public List<UserModel> listarUsuario() {
        List<UserModel> lista = repository.findAll();
        return lista;
    }

    // Método para criar usuarios no Bd
    public UserModel criarUsuario(UserModel usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getPassword()); // Senha é criptografada e o metodo encode() recebe a senha em texto plano e retorna a versao criptografada
        usuario.setPassword(encoder);
        UserModel novoUsuario = repository.save(usuario);
        return novoUsuario;
    }

    // Método para editar usuario no bd
    public UserModel editarUsuario(UserModel usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encoder);
        UserModel novoUsuario = repository.save(usuario);
        return novoUsuario;
    }

    // Método para deletar um usuario no bd
    public Boolean deletarUsuario(Long id) {
        repository.deleteById(id);
        return true;
    }

    // Método para validar a senha do usuario
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

    /*  listarUsuario(): Retorna todos os usuários do banco de dados.
        criarUsuario(): Cria um novo usuário, criptografando sua senha antes de salvar.
        editarUsuario(): Atualiza um usuário existente, criptografando sua senha antes de salvar.
        deletarUsuario(): Exclui um usuário do banco de dados.
        validarSenha(): Verifica se a senha fornecida pelo usuário corresponde à senha criptografada armazenada no banco de dados. 
    */

    /* 
     Essa classe UserService é uma parte fundamental da lógica de negócios relacionada ao gerenciamento de usuários no sistema de e-commerce, 
     envolvendo a criação, leitura, atualização, exclusão e validação de senhas. Ela também faz uso de boas práticas de segurança, 
     criptografando as senhas antes de armazená-las.
     */
}
