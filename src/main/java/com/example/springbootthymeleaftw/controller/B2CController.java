package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.model.entity.Warehouse;
import com.example.springbootthymeleaftw.model.entity.WarehouseStore;
import com.example.springbootthymeleaftw.repository.WarehouseProductRepository;
import com.example.springbootthymeleaftw.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/b2c")
@RequiredArgsConstructor
public class B2CController {
    private final SecurityService securityService;
    private final StoreService storeService;
    private final StoreProductService storePrService;
    private final WarehouseService warehouseService;
    private final WarehouseStoreService warehouseStoreService;
    private final WarehouseProductService warehouseProductService;
    private ArrayList<Store> stores;
    private final WarehouseProductRepository warehouseProductRepository;

    @GetMapping()
    public String open(Model model){
        if (!securityService.isAuthenticated()) {
            return "login";
        }

        UserEntity user = securityService.getUserEntity();

        stores = storeService.getAllStoresByAdminId(user.getId());
        ArrayList<Warehouse> warehouses = warehouseService.getAllActiveWarehouses();
        ArrayList<WarehouseStore> warehouseStores = warehouseStoreService.getWarehouseStoreByStatus("Linked");
        model.addAttribute("stores", stores);
        model.addAttribute("warehouses", warehouses);
        model.addAttribute("warehouseStores", warehouseStores);

        if (securityService.getUserEntity().getRole().getId() == 2)
            return "b2c";

        return "redirect:/";
    }

    @PostMapping("link")
    public String onLinkRequest(@RequestParam Long warehouseStoreId) {
        var warehouseStore = warehouseStoreService.getWarehouseStoreById(warehouseStoreId);
        warehouseStore.setStatus("Pending");
        warehouseStoreService.save(warehouseStore);

        return "redirect:/";
    }

    @PostMapping("buy")
    public String onBuyRequest(@RequestParam Long warehouseProductId) {
        var warehouseProd = warehouseProductService.getWarehouseProductById(warehouseProductId);
        Long prodId = warehouseProd.getProduct().getId();
        Long storeId = stores.get(0).getId(); // only one store for b2c so it works
        var storeProd = storePrService.getStoreProdByStoreAndProdId(storeId, prodId);

        long quantity = 100L;

        if (warehouseProd.getQuantity() >= quantity)
            warehouseProd.setQuantity(warehouseProd.getQuantity() - quantity);
        else {
            quantity = warehouseProd.getQuantity();
            warehouseProd.setQuantity(0L);
        }

        storeProd.setQuantity(storeProd.getQuantity() + quantity);

        warehouseProductService.save(warehouseProd);
        storePrService.save(storeProd);

        return "redirect:/";
    }
}