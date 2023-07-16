package org.bedu.atko.service;

import org.assertj.core.api.Assertions;
import org.bedu.atko.dto.Category.CreateCategoryDTO;
import org.bedu.atko.dto.Category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.entity.Category;
import org.bedu.atko.mapper.ICategoryMapper;
import org.bedu.atko.repository.ICategoryRepository;
import org.bedu.atko.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("Should save a category")
    void saveCategory(){
        Category category = Category.builder()
                .name("Sastreria")
                .build();

        CategoryDTO categoryDTO = CategoryDTO.builder().name("Sastreria").build();
        CreateCategoryDTO createCategoryDTO = CreateCategoryDTO.builder().name("Sastreria").build();

        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        when(mapper.toModel(createCategoryDTO)).thenReturn(category);
        when(mapper.toDTO(category)).thenReturn(categoryDTO);

        CategoryDTO savedCategory = categoryService.save(createCategoryDTO);

        Assertions.assertThat(savedCategory).isNotNull();

        verify(categoryRepository, times(1)).save(category);
        verify(mapper, times(1)).toModel(createCategoryDTO);
        verify(mapper, times(1)).toDTO(category);
    }

    @Test
    @DisplayName("Should find all categories")
    void findAllCategories(){
        List<Category> categoryList = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<CategoryDTO> found = categoryService.findAll();

        Assertions.assertThat(found).isNotNull();

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find category by id")
    void findCategoryById(){
        Category category = Category.builder()
                .id(1L)
                .name("Sastreria")
                .build();
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Sastreria").build();

        Optional<Category> categoryId = Optional.of(category);

        when(categoryRepository.findById(category.getId())).thenReturn(categoryId);
        when(mapper.toDTO(category)).thenReturn(categoryDTO);

        Optional<CategoryDTO> foundId = categoryService.findById(1L);

        Assertions.assertThat(foundId).isNotNull();

        verify(categoryRepository, times(1)).findById(1L);
        verify(mapper, times(1)).toDTO(category);
    }

    @Test
    @DisplayName("Should update a category")
    void updateCategory() {
        Category category = Category.builder()
                .name("Sastreria")
                .build();

        UpdateCategoryDTO updated = new UpdateCategoryDTO();
        updated.setName("Sastreria actualizado");

        assertThatThrownBy(() -> categoryService.update(category.getId(), updated));
    }

    @Test
    @DisplayName("Should delete a category")
    void deleteCategory(){
        Category category = Category.builder()
                .id(1L)
                .name("Sastreria")
                .build();

        categoryService.delete(category.getId());

        assertAll(() ->categoryService.delete(category.getId()));

    }
}