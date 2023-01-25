package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.Product;
import com.example.springbootthymeleaftw.model.entity.StoreProduct;
import com.example.springbootthymeleaftw.repository.StoreProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StoreProductService {
    private final StoreProductRepository storePr;

    public StoreProduct getStoreProdById(Long id)
    {
        return storePr.findById(id).get();
    }

    public StoreProduct getStoreProdByStoreAndProdId(Long storeId, Long prodId) {
        return storePr.getStoreProductByStoreIdAndProductId(storeId, prodId);
    }

    public void save(StoreProduct storeProduct) {
        storePr.save(storeProduct);
    }

    public Long getMaxId() {
        return storePr.getMaxId();
    }
}
