package org.bedu.atko.service;

import org.bedu.atko.dto.Category.CreateCategoryDTO;
import org.bedu.atko.dto.Category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;


import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    Optional<CategoryDTO> findById(long id);

    CategoryDTO save(CreateCategoryDTO data);

    void update(long id, UpdateCategoryDTO data);

    void delete(long id);
}
