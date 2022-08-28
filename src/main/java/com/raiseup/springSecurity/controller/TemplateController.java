package com.raiseup.springSecurity.controller;

import com.raiseup.springSecurity.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    private final ProductService productService;

    public TemplateController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("login")
    public String getLogin(){

        return "login";
    }

    @GetMapping("products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getAllProducts(0,25));
        return "products";
    }
}
