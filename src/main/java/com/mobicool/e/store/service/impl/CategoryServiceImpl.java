package com.mobicool.e.store.service.impl;

import com.mobicool.e.store.dto.CategoryDto;
import com.mobicool.e.store.dto.PageableResponse;
import com.mobicool.e.store.entity.Category;
import com.mobicool.e.store.exception.ResourceNotFoundException;
import com.mobicool.e.store.helper.Helper;
import com.mobicool.e.store.repository.CategoryRepo;
import com.mobicool.e.store.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        //creating categoryId randomlly
        String categoryId = UUID.randomUUID().toString();
        logger.info(" Initiated Request for getting category");
        categoryDto.setCategoryId(categoryId);
        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        logger.info(" Completed Request for getting category");

        return mapper.map(savedCategory,CategoryDto.class);
    }

   @Override // method pending
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        //get category of given id
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found exception"));
        logger.info(" Initiated Request for update category");

        //update category details
        category.setTitle(category.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepo.save(category);
       logger.info(" Completed Request for update category");

       return mapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found exception"));
        categoryRepo.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        logger.info(" Initiated Request for getting All category");

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Category> page = categoryRepo.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        logger.info(" Complete Request for getting  All category");

        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found exception"));
        logger.info(" Initiated Request for getting category");

        return mapper.map(category,CategoryDto.class);
    }
}
