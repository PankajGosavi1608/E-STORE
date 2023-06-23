package com.mobicool.e.store.service.impl;

import com.mobicool.e.store.dto.PageableResponse;
import com.mobicool.e.store.dto.ProductDto;
import com.mobicool.e.store.entity.Product;
import com.mobicool.e.store.exception.ResourceNotFoundException;
import com.mobicool.e.store.helper.Helper;
import com.mobicool.e.store.repository.ProductRepo;
import com.mobicool.e.store.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);

        //Product Id
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);

        //Added Date
        product.setAddedDate(new Date());

        Product saveProduct = productRepo.save(product);
        return mapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        //fetch product of given id
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found of Given Id"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());

        //save entity
        Product updatedProduct = productRepo.save(product);

        return mapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found of Given Id"));
        productRepo.delete(product);
    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found of Given Id"));

        return mapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize, String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepo.findAll(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse <ProductDto> getAllLive(int pageNumber,int pageSize, String sortBy,String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepo.findByLiveTrue(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse <ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize, String sortBy,String sortDir) {
            Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
            Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
            Page<Product> page = productRepo.findByTitleContaining(subTitle,pageable);
            return Helper.getPageableResponse(page,ProductDto.class);
    }
}
