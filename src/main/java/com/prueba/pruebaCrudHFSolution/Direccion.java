package com.prueba.pruebaCrudHFSolution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {

    private String calle;
    private String comuna;
    private String region;

}
