package org.bedu.atko.controller;

import jakarta.validation.Valid;
import org.bedu.atko.dto.category.CreateCategoryDTO;
import org.bedu.atko.dto.category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private ICategoryService service;

    @Autowired
    public CategoryController(ICategoryService service){
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CategoryDTO> findByID(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO save(@Valid @RequestBody CreateCategoryDTO data){
        return service.save(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UpdateCategoryDTO data, @PathVariable("id") long id){
        service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        service.delete(id);
    }
}
