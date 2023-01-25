package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.Product;
import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.repository.ProductRepository;
import com.example.springbootthymeleaftw.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }
}
