package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/") // this is default
@RequiredArgsConstructor
public class HomeController {
    private final SecurityService securityService;

    @GetMapping()
    public String open(Model model, String error, String logout){
        if (!securityService.isAuthenticated()) {
            return "login";
        }

        UserEntity user = securityService.getUserEntity();

        if (user.getRole().getId() == 2)
            return "redirect:/b2c";
        else if (user.getRole().getId() == 3)
            return "redirect:/b2b";

        return "redirect:/client";
    }
}
