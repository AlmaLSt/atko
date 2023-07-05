package org.bedu.atko.dto.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String name;

}
