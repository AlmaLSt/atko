package org.bedu.atko.dto;

import lombok.Data;

@Data
public class ProfessionalDTO {
    private long id;
    private String name;
    private int edad;
    private String telefono;
    private String email;
    private String areaTrabajo;
    private String categoria;
}
