package com.prueba.pruebaCrudHFSolution;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(PersonNotFoundException ex ){
        Map<String, Object> body = new HashMap<>();
        body.put("error", "NOT_FOUND");
        body.put("mensaje", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(RequestTimeoutException.class)
    public ResponseEntity<Map<String, Object>> handleTimeOut(RequestTimeoutException ex ){
        Map<String, Object> body = new HashMap<>();
        body.put("error", "REQUEST_TIMEOUT");
        body.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(body);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDBError(DataAccessException ex ){
        Map<String, Object> body = new HashMap<>();
        body.put("error", "DATABASE_ERROR");
        body.put("mensaje", "Error al acceder a la base de datos");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex ){
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err ->{
            String field = ((org.springframework.validation.FieldError) err).getField();
            String message = err.getDefaultMessage();
            errors.put(field, message);
        });
        Map<String, Object> body = new HashMap<>();
        body.put("error", "INTERNAL_ERROR");
        body.put("mensaje", "Ocurrio un error inesperado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
