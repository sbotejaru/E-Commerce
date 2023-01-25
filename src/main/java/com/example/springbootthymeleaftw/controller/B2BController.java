package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.*;
import com.example.springbootthymeleaftw.repository.WarehouseProductRepository;
import com.example.springbootthymeleaftw.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/b2b")
@RequiredArgsConstructor
public class B2BController {
    private final SecurityService securityService;
    private final WarehouseService warehouseService;
    private final WarehouseStoreService warehouseStoreService;
    private final WarehouseProductService warehouseProductService;
    private Warehouse warehouse;

    public class AddStock{
        public Long value;

        public AddStock() {
            value = 0L;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

    @GetMapping()
    public String open(Model model){
        if (!securityService.isAuthenticated()) {
            return "login";
        }

        UserEntity user = securityService.getUserEntity();

        ArrayList<WarehouseStore> warehouseStores = warehouseStoreService.getWarehouseStoresByStatus("Linked");
        model.addAttribute("warehouseStores", warehouseStores);

        warehouse = warehouseService.getAllWarehousesByAdminId(user.getId()).get(0);
        model.addAttribute("warehouse", warehouse);

        model.addAttribute("addStock", new AddStock());

        if (securityService.getUserEntity().getRole().getId() == 3)
            return "b2b";

        return "redirect:/";
    }

    @PostMapping("accept")
    public String onAcceptRequest(@RequestParam Long warehouseStoreId) {
        var warehouseStore = warehouseStoreService.getWarehouseStoreById(warehouseStoreId);
        warehouseStore.setStatus("Linked");
        warehouseStoreService.save(warehouseStore);

        return "redirect:/";
    }

    @PostMapping("deny")
    public String onDenyRequest(@RequestParam Long warehouseStoreId) {
        var warehouseStore = warehouseStoreService.getWarehouseStoreById(warehouseStoreId);
        warehouseStore.setStatus("None");
        warehouseStoreService.save(warehouseStore);

        return "redirect:/";
    }

    @PostMapping("add")
    public String onAddStock(@RequestParam Long warehouseProductId, @ModelAttribute AddStock addStock) {
        var warehouseProduct = warehouseProductService.getWarehouseProductById(warehouseProductId);
        warehouseProduct.setQuantity(warehouseProduct.getQuantity() + addStock.value);

        System.out.println(addStock.value);

        warehouseProductService.save(warehouseProduct);

        return "redirect:/";
    }
}
