package org.bedu.atko.dto.Professional;

import lombok.Data;

@Data
public class UpdateProfessionalDTO {
    private String name;
    private int edad;
    private String telefono;
    private String email;
    private String areaTrabajo;
    private String categoria;
}
