package com.prueba.pruebaCrudHFSolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PersonServiceTest {


    @Mock
    private PersonRepository repository;


    @InjectMocks
    private PersonServiceImpl service;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSavePerson() {
        Person person = Person.builder()
                .id(1L)
                .rut("12.345.678-9")
                .nombre("Juan")
                .apellido("Pérez")
                .fechaNacimiento(LocalDate.of(1990, 5, 20))
                .direccion(new Direccion("Av. Principal 123", "Las Condes", "Metropolitana"))
                .build();


        when(repository.save(any(Person.class))).thenReturn(person);


        PersonDTO dto = PersonDTO.fromEntity(person);
        PersonDTO saved = service.save(dto);


        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
//        verify(repository, times(1)).save(any(Person.class));
    }


    @Test
    void testFindByRut() {
        Person person = Person.builder()
                .id(1L)
                .rut("12.345.678-9")
                .nombre("Juan")
                .apellido("Pérez")
                .fechaNacimiento(LocalDate.of(1990, 5, 20))
                .build();


        when(repository.findByRut("12.345.678-9")).thenReturn(Optional.of(person));


        Optional<PersonDTO> result = service.findByRut("12.345.678-9");


        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
    }


    @Test
    void testUpdatePerson() {
        Person person = Person.builder()
                .id(1L)
                .rut("12.345.678-9")
                .nombre("Juan")
                .apellido("Pérez")
                .fechaNacimiento(LocalDate.of(1990, 5, 20))
                .build();


        when(repository.findByRut("12.345.678-9")).thenReturn(Optional.of(person));
        when(repository.save(any(Person.class))).thenReturn(person);


        PersonDTO dto = PersonDTO.fromEntity(person);
        dto.setNombre("Pedro");


        PersonDTO updated = service.update("12.345.678-9", dto);


        assertEquals("Pedro", updated.getNombre());
    }


    @Test
    void testDeletePersonNotFound() {
        when(repository.findByRut("99.999.999-9")).thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, () -> service.delete("99.999.999-9"));
    }
}