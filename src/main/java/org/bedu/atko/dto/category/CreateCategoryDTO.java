package org.bedu.atko.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateCategoryDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String name;

}
