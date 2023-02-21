package br.com.api.api.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String error;
    private LocalDateTime timestamp;
    private HttpStatus status;

}
