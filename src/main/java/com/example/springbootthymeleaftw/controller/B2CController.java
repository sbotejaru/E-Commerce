package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.*;
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
    private final ProductService productService;
    private Store store;

    @GetMapping()
    public String open(Model model){
        if (!securityService.isAuthenticated()) {
            return "login";
        }

        UserEntity user = securityService.getUserEntity();

        store = storeService.getAllStoresByAdminId(user.getId()).get(0);
        ArrayList<Warehouse> warehouses = warehouseService.getAllActiveWarehouses();
        ArrayList<WarehouseStore> warehouseStores = warehouseStoreService.getWarehouseStoreByStatusAndId("Linked", store.getId());
        model.addAttribute("store", store);
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
        Long storeId = store.getId();
        var storeProd = storePrService.getStoreProdByStoreAndProdId(storeId, prodId);

        long quantity = 100L;

        if (warehouseProd.getQuantity() >= quantity)
            warehouseProd.setQuantity(warehouseProd.getQuantity() - quantity);
        else {
            quantity = warehouseProd.getQuantity();
            warehouseProd.setQuantity(0L);
        }

        if (storeProd == null) {
            storeProd = new StoreProduct();
            storeProd.setId(storePrService.getMaxId() + 1);
            storeProd.setStore(store);
            storeProd.setProduct(warehouseProd.getProduct());
            storeProd.setQuantity(quantity);
        }
        else
            storeProd.setQuantity(storeProd.getQuantity() + quantity);

        warehouseProductService.save(warehouseProd);
        storePrService.save(storeProd);

        return "redirect:/";
    }
}
