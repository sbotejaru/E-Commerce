package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.Product;
import com.example.springbootthymeleaftw.model.entity.Store;
import com.example.springbootthymeleaftw.model.entity.StoreProduct;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.StoreProductRepository;
import com.example.springbootthymeleaftw.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final SecurityService securityService;
    private final StoreService storeService;
    private final StoreProductService storePrService;

    @GetMapping()
    public String open(Model model){
        if (!securityService.isAuthenticated()) {
            return "login";
        }

        ArrayList<Store> stores = storeService.getAllActiveStores();

        model.addAttribute("stores", stores);

        if (securityService.getUserEntity().getRole().getId() == 1)
            return "client";

        return "redirect:/";
    }

    @PostMapping("buy")
    public String onBuyProduct(@RequestParam Long storeProductId) {
       var storeProd = storePrService.getStoreProdById(storeProductId);
       storeProd.setQuantity(storeProd.getQuantity() - 1);

       storePrService.save(storeProd);

       return "redirect:/";
    }

}
