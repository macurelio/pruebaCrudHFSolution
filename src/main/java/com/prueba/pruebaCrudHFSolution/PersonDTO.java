package com.prueba.pruebaCrudHFSolution;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
public class PersonDTO {
    @NotBlank
    private String rut;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate fechaNacimiento;
    private String calle;
    private String comuna;
    private String region;
    private Integer edad;

    public static PersonDTO fromEntity(Person p) {
        Integer age = Period.between(p.getFechaNacimiento(), LocalDate.now()).getYears();
        return PersonDTO.builder()
                .rut(p.getRut())
                .nombre(p.getNombre())
                .apellido(p.getApellido())
                .fechaNacimiento(p.getFechaNacimiento())
                .calle(p.getDireccion() != null ? p.getDireccion().getCalle() : null)
                .comuna(p.getDireccion() != null ? p.getDireccion().getComuna() : null)
                .region(p.getDireccion() != null ? p.getDireccion().getRegion() : null)
                .edad(age)
                .build();
    }
}
