package org.bedu.atko.mapper;

import javax.annotation.processing.Generated;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.dto.review.CreateReviewDTO;
import org.bedu.atko.dto.review.UpdateReviewDTO;
import org.bedu.atko.entity.Reviews;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T18:34:39-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class IReviewMapperImpl implements IReviewMapper {

    @Override
    public ReviewDTO toDTO(Reviews data) {
        if ( data == null ) {
            return null;
        }

        ReviewDTO.ReviewDTOBuilder reviewDTO = ReviewDTO.builder();

        reviewDTO.id( data.getId() );
        reviewDTO.professional( data.getProfessional() );
        reviewDTO.clients( data.getClients() );
        reviewDTO.description( data.getDescription() );

        return reviewDTO.build();
    }

    @Override
    public Reviews toModel(CreateReviewDTO data) {
        if ( data == null ) {
            return null;
        }

        Reviews reviews = new Reviews();

        reviews.setProfessional( data.getProfessional() );
        reviews.setClients( data.getClients() );
        reviews.setDescription( data.getDescription() );

        return reviews;
    }

    @Override
    public void toModel(Reviews entity, UpdateReviewDTO data) {
        if ( data == null ) {
            return;
        }

        if ( data.getProfessional() != null ) {
            entity.setProfessional( data.getProfessional() );
        }
        if ( data.getClients() != null ) {
            entity.setClients( data.getClients() );
        }
        if ( data.getDescription() != null ) {
            entity.setDescription( data.getDescription() );
        }
    }
}
