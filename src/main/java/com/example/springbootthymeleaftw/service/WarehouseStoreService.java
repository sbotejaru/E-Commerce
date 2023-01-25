package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.Product;
import com.example.springbootthymeleaftw.model.entity.StoreProduct;
import com.example.springbootthymeleaftw.model.entity.WarehouseStore;
import com.example.springbootthymeleaftw.repository.StoreProductRepository;
import com.example.springbootthymeleaftw.repository.WarehouseStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WarehouseStoreService {
    private final WarehouseStoreRepository warehouseStoreRepository;

    public WarehouseStore getWarehouseStoreById(Long id)
    {
        return warehouseStoreRepository.findById(id).get();
    }

    public void save(WarehouseStore warehouseStore) {
        warehouseStoreRepository.save(warehouseStore);
    }

    public ArrayList<WarehouseStore> getWarehouseStoreByStatus(String status) {
        return warehouseStoreRepository.getWarehouseStoreByStatus(status);
    }
}
