package com.mobicool.e.store.service.impl;

import com.mobicool.e.store.dto.CategoryDto;
import com.mobicool.e.store.dto.PageableResponse;
import com.mobicool.e.store.entity.Category;
import com.mobicool.e.store.exception.ResourceNotFoundException;
import com.mobicool.e.store.repository.CategoryRepo;
import com.mobicool.e.store.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return mapper.map(savedCategory,CategoryDto.class);
    }

   @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        //get category of given id
        /*Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found exception"));

        //update category details
        category.setTitle(category.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepo.save(category);*/
        return null;
    }

    @Override
    public void delete(String categoryId) {

    }

    @Override
    public PageableResponse<CategoryDto> getAll() {
        return null;
    }

    @Override
    public CategoryDto get(String categoryId) {
        return null;
    }
}
