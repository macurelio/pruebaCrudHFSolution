package com.prueba.pruebaCrudHFSolution;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    PersonDTO save(PersonDTO dto);
    Optional<Person> findByRut (String rut);
    List<Person> findAll();
    Person update (String rut, Person dto);
    void delete(String rut);

}
