package com.example.basicauthentication.controller;

import com.example.basicauthentication.entity.Product;
import com.example.basicauthentication.service.Impl.ProductServiceImpl;
import com.example.basicauthentication.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;
    @GetMapping
    public List<Product> getAll(){
        return productService.getALLProduct();
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @DeleteMapping
    public void deleteById(Long id){
        productService.deleteById(id);
    }
}
