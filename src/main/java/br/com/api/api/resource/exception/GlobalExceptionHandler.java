package br.com.api.api.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErrorMessage> EmailJaCadastradoException(EmailJaCadastradoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                ErrorMessage(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST));

    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> UsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                ErrorMessage(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST));

    }
}
