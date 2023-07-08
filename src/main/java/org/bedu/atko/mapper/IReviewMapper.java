package org.bedu.atko.mapper;


import org.bedu.atko.dto.Review.CreateReviewDTO;
import org.bedu.atko.dto.Review.UpdateReviewDTO;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.entity.Reviews;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IReviewMapper {
    ReviewDTO toDTO(Reviews data);

    @Mapping(target = "id", ignore = true)
    Reviews toModel(CreateReviewDTO data);

    @Mapping(target = "id", ignore = true)
    void toModel(@MappingTarget Reviews entity, UpdateReviewDTO data);
}
