package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Override
    Optional<Store> findById(Long id);

    Optional<Store> getStoreById(Long id);
    ArrayList<Store> getStoresByStatus(String status);

    ArrayList<Store> getStoresByAdmin_Id(Long id);
}
