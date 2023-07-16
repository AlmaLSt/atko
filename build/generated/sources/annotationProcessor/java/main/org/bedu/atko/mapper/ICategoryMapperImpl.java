package org.bedu.atko.mapper;

import javax.annotation.processing.Generated;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.dto.category.CreateCategoryDTO;
import org.bedu.atko.dto.category.UpdateCategoryDTO;
import org.bedu.atko.entity.Category;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T18:34:39-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ICategoryMapperImpl implements ICategoryMapper {

    @Override
    public CategoryDTO toDTO(Category data) {
        if ( data == null ) {
            return null;
        }

        CategoryDTO.CategoryDTOBuilder categoryDTO = CategoryDTO.builder();

        categoryDTO.id( data.getId() );
        categoryDTO.name( data.getName() );

        return categoryDTO.build();
    }

    @Override
    public Category toModel(CreateCategoryDTO data) {
        if ( data == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( data.getName() );

        return category;
    }

    @Override
    public void toModel(Category entity, UpdateCategoryDTO data) {
        if ( data == null ) {
            return;
        }

        if ( data.getName() != null ) {
            entity.setName( data.getName() );
        }
    }
}
