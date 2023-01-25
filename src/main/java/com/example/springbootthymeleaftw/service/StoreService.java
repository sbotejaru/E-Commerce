package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public ArrayList<Store> getAllActiveStores() {
        return storeRepository.getStoresByStatus("Active");
    }

    public ArrayList<Store> getAllStoresByAdminId(Long id) {
        return storeRepository.getStoresByAdmin_Id(id);
    }
}
