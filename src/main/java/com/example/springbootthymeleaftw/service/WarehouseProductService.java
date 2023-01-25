package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.Product;
import com.example.springbootthymeleaftw.model.entity.StoreProduct;
import com.example.springbootthymeleaftw.model.entity.WarehouseProduct;
import com.example.springbootthymeleaftw.model.entity.WarehouseStore;
import com.example.springbootthymeleaftw.repository.StoreProductRepository;
import com.example.springbootthymeleaftw.repository.WarehouseProductRepository;
import com.example.springbootthymeleaftw.repository.WarehouseStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WarehouseProductService {
    private final WarehouseProductRepository warehouseProductRepository;

    public WarehouseProduct getWarehouseProductById(Long id)
    {
        return warehouseProductRepository.findById(id).get();
    }

    public void save(WarehouseProduct warehouseProduct) {
        warehouseProductRepository.save(warehouseProduct);
    }
}
