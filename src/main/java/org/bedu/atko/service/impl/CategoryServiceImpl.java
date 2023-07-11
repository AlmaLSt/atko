package org.bedu.atko.service.impl;


import org.bedu.atko.dto.Category.CreateCategoryDTO;
import org.bedu.atko.dto.Category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.entity.Category;
import org.bedu.atko.exception.CategoryNotFoundException;
import org.bedu.atko.mapper.ICategoryMapper;
import org.bedu.atko.repository.ICategoryRepository;
import org.bedu.atko.service.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements ICategoryService {

    private ICategoryRepository repository;
    private ICategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(ICategoryRepository repository, ICategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> findAll(){
        List<Category> categories = repository.findAll();
        return categories.stream().map(mapper::toDTO).toList();
    }

    public Optional<CategoryDTO> findById(long id){
        Optional<Category> category = repository.findById(id);
        return category.isPresent() ? Optional.of(mapper.toDTO(category.get())) : Optional.of(null);
    }

    public CategoryDTO save(CreateCategoryDTO data){
        Category entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(long id, UpdateCategoryDTO data){
        Optional<Category> current = repository.findById(id);
        if (!current.isPresent()){
            throw new CategoryNotFoundException();
        }
        Category category = current.get();
        mapper.toModel(category, data);
        repository.save(category);
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
