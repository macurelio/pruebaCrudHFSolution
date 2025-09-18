package com.prueba.pruebaCrudHFSolution;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PersonNotFoundException extends RuntimeException{
     public PersonNotFoundException(String rut){
         super("Persona con rut " + rut + " no encontrada");
     }


}
