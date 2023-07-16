package org.bedu.atko.dto.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateCategoryDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String name;

}
