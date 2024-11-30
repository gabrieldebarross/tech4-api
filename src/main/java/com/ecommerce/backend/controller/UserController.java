package com.ecommerce.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.model.UserModel;
import com.ecommerce.backend.service.UserService;

import jakarta.validation.Valid;

@RestController // Indica que esta classe é um controlador REST no Spring, ou seja, ela irá responder a requisições HTTP
@CrossOrigin("*") // Permite que o controller aceite requisições de qualquer origem
@RequestMapping("/user") // Define o caminho base para os endpoints do controller, ou seja, todos os endpoints dentro desta classe começam com /user
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping // Define um endpoint HTTP GET para o caminho /user
    public ResponseEntity<List<UserModel>> ListaUsuario() {
        return ResponseEntity.status(200).body(userService.listarUsuario());
    }

    @PostMapping // Define um endpoint HTTP POST para o caminho /user
    public ResponseEntity<UserModel> criarUsuario(@Valid @RequestBody UserModel usuario){
        return ResponseEntity.status(201).body(userService.criarUsuario(usuario));
    }

    @PutMapping // Define um endpoint HTTP PUT para o caminho /user.
    public ResponseEntity<UserModel> editarUsuario(@Valid @RequestBody UserModel usuario){
        return ResponseEntity.status(201).body(userService.editarUsuario(usuario));
    }

    @DeleteMapping("/{id}") // Define um endpoint HTTP DELETE para o caminho /user/{id}, onde {id} é um parâmetro de caminho (usando @PathVariable).
    public ResponseEntity<?> deletarUsuario (@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login") //  Define um endpoint HTTP POST para o caminho /user/login.
    public ResponseEntity<UserModel> validarSenha(@Valid @RequestBody UserModel usuario) {
        Boolean valid = userService.validarSenha(usuario);
        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } 
        return ResponseEntity.status(200).build();
    }

    // Tratamento de Erros de Validação
    @ResponseStatus(HttpStatus.BAD_REQUEST) // A anotação @ResponseStatus indica que, quando esse método for chamado, ele vai retornar uma resposta HTTP com o status especificado
    @ExceptionHandler(MethodArgumentNotValidException.class) // Trata exceções que ocorrem quando a validação de um parâmetro de método falha
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>(); // O método retorna um mapa (Map<String, String>) com os nomes dos campos e suas respectivas mensagens de erro.

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

            // Método getBindResult() retorna o resultado dos dados de entrada enviados na requisicao com o objeto que será validado, que será o UserModel, contém infos sobre erros de validação.
            // Método getAllErrors retorna uma lista de todos os erros durante a validacao
            // Método forEach() serve para iterar sobre os erros encontrados
            // Método FieldError() Contém infos sobre o campo que falhou e a mensagem associada. 
            // Método getField() retorna o nome do campo(atributo) do obj que causou o erro de validacao. Se for username do obj UserModel estiver vazio, o nome do campo sera username
            // Método getDefaultMessage() retorna a mensagem de erro associada ao campo
            // errors.put() insere o nome do campo (fieldName) e a mensagem de erro (errorMessage) no mapa errors. Armazenando um par de chava-valor. Chave: nome do campo com erro e Valor: mensagem de erro associada ao campo
        });
        return errors;
    }
    
}
