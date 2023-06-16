package com.mobicool.e.store.service;

import com.mobicool.e.store.dto.CategoryDto;
import com.mobicool.e.store.dto.PageableResponse;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto,String categoryId);

    //delete
    void delete(String categoryId);

    //get all

    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);

    //get single category by details

    CategoryDto get(String categoryId);

    //search

}
