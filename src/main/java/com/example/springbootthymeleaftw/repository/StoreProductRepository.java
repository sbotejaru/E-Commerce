package com.example.springbootthymeleaftw.repository;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.StoreProduct;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {

    @Override
    Optional<StoreProduct> findById(Long aLong);

    ArrayList<StoreProduct> getStoreProductsByStoreId(Long id);

    StoreProduct getStoreProductByStoreIdAndProductId(Long id1, Long id2);

    @Query("SELECT max(id) FROM StoreProduct")
    Long getMaxId();
}
