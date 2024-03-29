package com.example.backend_demo.controller;

import com.example.backend_demo.exeption.ProductException;
import com.example.backend_demo.model.Product;
import com.example.backend_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> color,
                                                                      @RequestParam List<String> size, @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
                                                                      @RequestParam String sort, @RequestParam String stock,
                                                                      @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Product> response = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/{category}")
    public ResponseEntity<List<Product>> findAllProductByCategoryHandler(@PathVariable String category) {
        List<Product> response = productService.findAllProductsByCategory(category);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }

    //    @GetMapping("/products/search")
    //    public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String search){
    //        List<Product> productList=productService.searchProduct(search);
    //
    //        return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
    //    }


}
