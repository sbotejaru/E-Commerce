package com.example.springbootthymeleaftw.model.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private UserEntity admin;

    @OneToMany(mappedBy = "warehouse")
    private Set<WarehouseStore> warehouseStores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "warehouse")
    private Set<WarehouseProduct> warehouseProducts = new LinkedHashSet<>();

    @Column(name = "active", nullable = false, length = 16)
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public Set<WarehouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(Set<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

}