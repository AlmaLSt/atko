package org.bedu.atko.repository;

import org.bedu.atko.entity.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.atko")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ICategoryRepositoryTest {

    @Autowired
    private ICategoryRepository categoryRepository;

    Category category;

    @BeforeAll
    void setUp(){
        category = new Category();
        category.setName("Mecánica");
    }

    @AfterAll
    void cleanUp(){
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Should get all categories")
    void findAll(){
        List<Category> categories = categoryRepository.findAll();
        assertEquals(categoryRepository.findAll().size(), categories.size());
    }

    @Test
    @DisplayName("Should get category by id")
    void findById(){
        category = categoryRepository.save(category);
        Optional<Category> findCategory = categoryRepository.findById(category.getId());
        assertEquals(category.getId(), findCategory.get().getId());
    }

    @Test
    @DisplayName("Should save a new category")
    void save(){
        category = categoryRepository.save(category);
        assertNotNull(category.getId());
    }

    @Test
    @DisplayName("Should update a category")
    void update(){
        categoryRepository.save(category);
        Category updatedCategory = categoryRepository.findById(category.getId()).get();
        updatedCategory.setName("Mecánica Automotriz");

        updatedCategory = categoryRepository.save(updatedCategory);

        assertEquals(updatedCategory.getName(), "Mecánica Automotriz");
    }

    @Test
    @DisplayName("Should delete a category")
    void delete(){
        List<Category> categories = categoryRepository.findAll();
        int size = categories.size();

        categoryRepository.delete(category);

        categories = categoryRepository.findAll();
        assertEquals(categoryRepository.findAll().size(), categories.size());
        }
    }
