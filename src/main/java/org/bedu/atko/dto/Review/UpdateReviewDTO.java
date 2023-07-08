package org.bedu.atko.dto.Review;

import lombok.Data;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;

@Data
public class UpdateReviewDTO {

    private Professional professional;

    private Client clients;

    private String description;

}
