package org.bedu.atko.dto.Professional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateProfessionalDTO {
    private String name;
    private int edad;
    private String telefono;
    private String email;
    private String areaTrabajo;
    private String categoria;
}
