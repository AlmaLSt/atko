package org.bedu.atko.service;

import org.assertj.core.api.Assertions;
import org.bedu.atko.dto.review.CreateReviewDTO;
import org.bedu.atko.dto.review.UpdateReviewDTO;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.entity.Reviews;
import org.bedu.atko.exception.ReviewNotFoundException;
import org.bedu.atko.mapper.IReviewMapper;
import org.bedu.atko.repository.IReviewRepository;
import org.bedu.atko.service.impl.ReviewsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServicesTest {

    @Mock
    private IReviewRepository reviewRepository;

    @Mock
    private IReviewMapper mapper;

    @InjectMocks
    private ReviewsServiceImpl reviewsService;

    @Test
    @DisplayName("Should save a review")
    void saveReview(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        Set<Client> employmentContracts = new HashSet<>();
        Professional professional = Professional.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();
        Client client = Client.builder().id(1L).name("Ana").edad(27).telefono("123456789").email("ana@gmail.com").build();

        Reviews reviews = Reviews.builder().professional(professional).clients(client).description("This is a review").build();
        ReviewDTO reviewDTO = ReviewDTO.builder().professional(professional).clients(client).description("This is a review").build();
        CreateReviewDTO createReviewDTO = CreateReviewDTO.builder().professional(professional).clients(client).description("This is a review").build();

        when(reviewRepository.save(Mockito.any(Reviews.class))).thenReturn(reviews);
        when(mapper.toModel(createReviewDTO)).thenReturn(reviews);
        when(mapper.toDTO(reviews)).thenReturn(reviewDTO);

        ReviewDTO savedReview = reviewsService.save(createReviewDTO);

        Assertions.assertThat(savedReview).isNotNull();

        verify(reviewRepository, times(1)).save(reviews);
        verify(mapper, times(1)).toModel(createReviewDTO);
        verify(mapper, times(1)).toDTO(reviews);
    }

    @Test
    @DisplayName("Should find all reviews")
    void findAllReviews(){
        List<Reviews> reviewsList = new ArrayList<>();

        when(reviewRepository.findAll()).thenReturn(reviewsList);

        List<ReviewDTO> found = reviewsService.getAll();

        Assertions.assertThat(found).isNotNull();

        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find review by id")
    void findReviewById(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        Set<Client> employmentContracts = new HashSet<>();
        Professional professional = Professional.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();
        Client client = Client.builder().id(1L).name("Ana").edad(27).telefono("123456789").email("ana@gmail.com").build();

        Reviews reviews = Reviews.builder().professional(professional).clients(client).description("This is a review").build();
        ReviewDTO reviewDTO = ReviewDTO.builder().professional(professional).clients(client).description("This is a review").build();

        List<Reviews> reviewByProfessional = List.of(reviews);

        when(reviewRepository.findByProfessional_id(professional.getId())).thenReturn(reviewByProfessional);
        when(mapper.toDTO(reviews)).thenReturn(reviewDTO);

        List<ReviewDTO> foundByProfessionalId = reviewsService.getByProfessional(professional.getId());

        Assertions.assertThat(foundByProfessionalId).isNotNull();

        verify(reviewRepository, times(1)).findByProfessional_id(professional.getId());
        verify(mapper, times(1)).toDTO(reviews);
    }

    @Test
    @DisplayName("Should update a review")
    void updateReview(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        Set<Client> employmentContracts = new HashSet<>();
        Professional professional = Professional.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();

        UpdateReviewDTO updateReview = UpdateReviewDTO.builder().description("An updated review").build();

        assertThatThrownBy(() -> reviewsService.update(updateReview, professional.getId()))
                .isInstanceOf(ReviewNotFoundException.class);
    }

    @Test
    @DisplayName("Should delete a review")
    void deleteReview(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        Set<Client> employmentContracts = new HashSet<>();
        Professional professional = Professional.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();
        Client client = Client.builder().id(1L).name("Ana").edad(27).telefono("123456789").email("ana@gmail.com").build();

        Reviews reviews = Reviews.builder().professional(professional).clients(client).description("This is a review").build();

        reviewsService.delete(reviews.getId());

        assertAll(() -> reviewsService.delete(reviews.getId()));
    }

}