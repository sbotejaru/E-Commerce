package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct, Long> {
    @Override
    Optional<WarehouseProduct> findById(Long aLong);

    ArrayList<WarehouseProduct> getWarehouseProductById(Long id);
}
