package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.Warehouse;
import com.example.springbootthymeleaftw.repository.StoreRepository;
import com.example.springbootthymeleaftw.repository.WarehouseRepository;
import com.example.springbootthymeleaftw.repository.WarehouseStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseStoreRepository warehouseStoreRepository;

    public ArrayList<Warehouse> getAllActiveWarehouses() {
        return warehouseRepository.getWarehousesByActive("Active");
    }

    public ArrayList<Warehouse> getAllWarehousesByAdminId(Long id) {
        return warehouseRepository.getWarehousesByAdmin_Id(id);
    }
}
