package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Override
    Optional<Warehouse> findById(Long id);

    Optional<Warehouse> getWarehouseById(Long id);

    ArrayList<Warehouse> getWarehousesByAdmin_Id(Long id);

    ArrayList<Warehouse> getWarehousesByActive(String active);
}
