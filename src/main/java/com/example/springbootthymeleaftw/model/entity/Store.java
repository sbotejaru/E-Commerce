package com.example.springbootthymeleaftw.model.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private UserEntity admin;

    @OneToMany(mappedBy = "store")
    private Set<WarehouseStore> warehouseStores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<StoreProduct> storeProducts = new LinkedHashSet<>();

    @Column(name = "status", nullable = false, length = 16)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }

    public Set<WarehouseStore> getWarehouseStores() {
        return warehouseStores;
    }

    public void setWarehouseStores(Set<WarehouseStore> warehouseStores) {
        this.warehouseStores = warehouseStores;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<StoreProduct> getStoreProducts() {
        return storeProducts;
    }

    public void setStoreProducts(Set<StoreProduct> storeProducts) {
        this.storeProducts = storeProducts;
    }

}