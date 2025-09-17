package com.prueba.pruebaCrudHFSolution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {
    Optional<Person> findByRut(String rut);

}
