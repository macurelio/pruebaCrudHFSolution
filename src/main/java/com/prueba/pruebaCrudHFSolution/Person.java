package com.prueba.pruebaCrudHFSolution;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name ="persons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Embedded
    private Direccion direccion;


}
