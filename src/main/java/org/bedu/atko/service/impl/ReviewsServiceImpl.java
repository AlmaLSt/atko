package org.bedu.atko.service.impl;

import org.bedu.atko.dto.review.CreateReviewDTO;
import org.bedu.atko.dto.review.UpdateReviewDTO;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.entity.Reviews;
import org.bedu.atko.exception.ReviewNotFoundException;
import org.bedu.atko.mapper.IReviewMapper;
import org.bedu.atko.repository.IReviewRepository;
import org.bedu.atko.service.IReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements IReviewServices {

    private IReviewRepository repository;
    private IReviewMapper mapper;

    @Autowired
    public ReviewsServiceImpl(IReviewRepository repository, IReviewMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ReviewDTO> getAll() {
        List<Reviews> reviews = repository.findAll();
        return reviews.stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<ReviewDTO> getByProfessional(long id) {
        List<Reviews> reviews = repository.findByProfessional_id(id);
        return reviews.stream().map(mapper::toDTO).toList();
    }

    @Override
    public ReviewDTO save(CreateReviewDTO reviews) {
        Reviews entity = repository.save(mapper.toModel(reviews));
        return mapper.toDTO(entity);
    }

    @Override
    public void update(UpdateReviewDTO reviews, long id) {
        Optional<Reviews> current = repository.findById(id);
        if (!current.isPresent()) {
            throw new ReviewNotFoundException();
        }
        Reviews reviews1 = current.get();
        mapper.toModel(reviews1, reviews);
        repository.save(reviews1);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
