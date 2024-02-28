package com.example.basicauthentication.service.Impl;

import com.example.basicauthentication.entity.Product;
import com.example.basicauthentication.repository.ProductRepository;
import com.example.basicauthentication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product getById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<Product> getALLProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product product1 = productRepository.getReferenceById(product.getId());
        product1.setName(product.getName());
        return productRepository.save(product1);
    }

    @Override
    public void deleteById(Long id) {
       productRepository.deleteById(id);
    }
}
