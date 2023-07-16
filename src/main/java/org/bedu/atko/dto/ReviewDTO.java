package org.bedu.atko.dto;

import lombok.*;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReviewDTO {

    private long id;

    private Professional professional;

    private Client clients;

    private String description;
}
