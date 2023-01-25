package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.StoreProduct;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.model.entity.WarehouseStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface WarehouseStoreRepository extends JpaRepository<WarehouseStore, Long> {

    @Override
    Optional<WarehouseStore> findById(Long aLong);

    ArrayList<WarehouseStore> getWarehouseStoreById(Long id);

    ArrayList<WarehouseStore> getWarehouseStoreByStatus(String status);
}
