package com.prueba.pruebaCrudHFSolution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PersonController.class)
class PersonControllerTest {


    @Autowired
    private MockMvc mockMvc;

@MockitoBean
private PersonService service;


@Test
void testGetAllPersons() throws Exception {
    PersonDTO person = PersonDTO.builder()
            .rut("12.345.678-9")
            .nombre("Juan")
            .apellido("Pérez")
            .fechaNacimiento(LocalDate.of(1990, 5, 20))
            .edad(34)
            .build();


    when(service.findAll()).thenReturn(List.of(person));


    mockMvc.perform(get("/api/v1/persons"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Juan"));
}


@Test
void testGetByRut() throws Exception {
    PersonDTO person = PersonDTO.builder()
            .rut("12.345.678-9")
            .nombre("Juan")
            .apellido("Pérez")
            .fechaNacimiento(LocalDate.of(1990, 5, 20))
            .edad(34)
            .build();


    when(service.findByRut("12.345.678-9")).thenReturn(Optional.of(person));


    mockMvc.perform(get("/api/v1/persons/12.345.678-9"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Juan"));
}


@Test
void testCreatePerson() throws Exception {
    PersonDTO person = PersonDTO.builder()
            .rut("12.345.678-9")
            .nombre("Juan")
            .apellido("Pérez")
            .fechaNacimiento(LocalDate.of(1990, 5, 20))
            .edad(34)
            .build();


    when(service.save(any(PersonDTO.class))).thenReturn(person);


    String json = "{" +
            "\"rut\":\"12.345.678-9\", " +
            "\"nombre\":\"Juan\", " +
            "\"apellido\":\"Pérez\", " +
            "\"fechaNacimiento\":\"1990-05-20\"}";


    mockMvc.perform(post("/api/v1/persons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.rut").value("12.345.678-9"));
}
}