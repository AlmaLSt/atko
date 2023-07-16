package org.bedu.atko.mapper;

import org.bedu.atko.dto.category.CreateCategoryDTO;
import org.bedu.atko.dto.category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ICategoryMapper {
    CategoryDTO toDTO(Category data);

    @Mapping(target = "id", ignore = true)
    Category toModel(CreateCategoryDTO data);

    @Mapping(target = "id", ignore = true)
    void toModel(@MappingTarget Category entity, UpdateCategoryDTO data);

}
