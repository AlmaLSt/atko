package org.bedu.atko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProfessionalDTO {
    private long id;
    private String name;
    private int edad;
    private String telefono;
    private String email;
    private String areaTrabajo;
    private String categoria;
}
