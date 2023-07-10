package org.bedu.atko.dto.Professional;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateProfessionalDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @Min(value = 0, message = "La edad debe ser mayor a {value}")
    @Max(value = 100, message = "La edad debe ser menor a {value}")
    private int edad;
    @Pattern(regexp = "\\d{3}[ -]?\\d{3}[ -]?\\d{4}", message = "El teléfono debe tener un formato de ##-####-####")
    private String telefono;
    @Email
    private String email;
    @NotBlank(message = "Indique un área de trabajo")
    private String areaTrabajo;

    @NotBlank(message = "Ingrese una categoria")
    private String categoria;
}
