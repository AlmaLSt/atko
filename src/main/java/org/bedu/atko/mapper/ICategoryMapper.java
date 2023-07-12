package org.bedu.atko.mapper;

import org.bedu.atko.dto.Category.CreateCategoryDTO;
import org.bedu.atko.dto.Category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Professional;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ICategoryMapper {
    CategoryDTO toDTO(Category data);

    @Mapping(target = "id", ignore = true)
    Category toModel(CreateCategoryDTO data);

    @Mapping(target = "id", ignore = true)
    void toModel(@MappingTarget Category entity, UpdateCategoryDTO data);

}
