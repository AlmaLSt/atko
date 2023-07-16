package org.bedu.atko.dto.review;

import lombok.*;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateReviewDTO {

    private Professional professional;

    private Client clients;

    private String description;

}
