package com.prueba.pruebaCrudHFSolution;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RequestTimeoutException extends RuntimeException{
    public RequestTimeoutException (String message){
        super(message);
    }
}
