package org.bedu.atko.dto.review;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateReviewDTO {

    @NotNull(message = "Debe indicar el id del profesional")
    private Professional professional;
    @NotNull(message = "Debe indicar el id del cliente")
    private Client clients;
    @NotBlank(message = "Debe escribir un review")
    private String description;
}
