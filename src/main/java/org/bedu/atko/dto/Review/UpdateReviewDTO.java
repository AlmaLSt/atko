package org.bedu.atko.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
