package org.bedu.atko.controller;

import jakarta.validation.Valid;
import org.bedu.atko.dto.Review.CreateReviewDTO;
import org.bedu.atko.dto.Review.UpdateReviewDTO;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.service.IReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    private IReviewServices services;

    @Autowired
    public  ReviewController(IReviewServices services){
        this.services = services;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> findAll(){
        return services.getAll();
    }

    @GetMapping("{professional_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> findByProfessional(@PathVariable(name = "professional_id") long professional_id){
        return services.getByProfessional(professional_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDTO save(@Valid @RequestBody CreateReviewDTO reviews){
        return services.save(reviews);
    }

    @PutMapping("{review_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UpdateReviewDTO reviews, @PathVariable(name = "review_id") long review_id){
        services.update(reviews, review_id);
    }

    @DeleteMapping("{review_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "review_id") long review_id){
        services.delete(review_id);
    }

}
