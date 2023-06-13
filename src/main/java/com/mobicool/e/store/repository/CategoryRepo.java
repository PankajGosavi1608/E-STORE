package com.mobicool.e.store.repository;

import com.mobicool.e.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {
}
