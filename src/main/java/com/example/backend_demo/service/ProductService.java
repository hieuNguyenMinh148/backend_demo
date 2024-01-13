package com.example.backend_demo.service;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProducts();

    public Product crateProduct(CreateProductRequest request);

    public String deleteProduct(Long id) throws ProductException;

    public Product updateProduct(Long id, Product request) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category);

    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
                                       Integer maxPrice, Integer minDiscount, String sort, String stock,
                                       Integer pageNumber, Integer pageSize);

}
