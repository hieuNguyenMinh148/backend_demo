package com.example.backend_demo.controller;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.request.CreateProductRequest;
import com.example.backend_demo.response.ApiResponse;
import com.example.backend_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.crateProduct(request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        ApiResponse response = new ApiResponse();
        response.setMessage("Product deleted successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> productList = productService.findAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product request)
            throws ProductException {
        Product product = productService.updateProduct(productId, request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] request) {
        for (CreateProductRequest productRequest : request) {
            productService.crateProduct(productRequest);
        }
        ApiResponse response = new ApiResponse("All Product created successfully", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
