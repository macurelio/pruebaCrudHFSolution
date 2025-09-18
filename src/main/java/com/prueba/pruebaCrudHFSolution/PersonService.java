package com.prueba.pruebaCrudHFSolution;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    PersonDTO save(PersonDTO dto);
    Optional<PersonDTO> findByRut (String rut);
    List<PersonDTO> findAll();
    PersonDTO update (String rut, PersonDTO dto);
    void delete(String rut);

}
