package com.mobicool.e.store.repository;

import com.mobicool.e.store.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String> {

    //Search

    Page<Product> findByTitleContaining(String subTitle,Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);

    //Other Method

    //Custom Finder

    //Query Methods


}
