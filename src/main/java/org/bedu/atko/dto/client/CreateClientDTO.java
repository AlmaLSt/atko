package org.bedu.atko.dto.client;

import jakarta.validation.constraints.*;
import lombok.*;
import org.bedu.atko.entity.Professional;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateClientDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Min(value = 0, message = "La edad debe ser mayor a {value}")
    @Max(value =100, message = "La edad deber ser menor a {value}")
    private int edad;

    @Pattern(regexp = "\\d{3}[ -]?\\d{3}[ -]?\\d{4}", message = "El teléfono debe tener un formato de ##-####-####")
    private String telefono;

    @Email
    private String email;

    private Set<Professional> hired;

}
