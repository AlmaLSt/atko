package org.bedu.atko.service;

import org.bedu.atko.dto.Review.CreateReviewDTO;
import org.bedu.atko.dto.Review.UpdateReviewDTO;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.entity.Reviews;

import java.util.List;

public interface IReviewServices {

    ReviewDTO save(CreateReviewDTO reviews);
    List<ReviewDTO> getAll();

    List<ReviewDTO> getByProfessional(long id);

    void update(UpdateReviewDTO reviews, long id);

    void delete(long id);
}
