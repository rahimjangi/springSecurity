package com.raiseup.springSecurity.controller;

import com.raiseup.springSecurity.entity.Product;
import com.raiseup.springSecurity.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/products",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product>getProduct(@PathVariable("productId")String productId){
        Product product = productService.getProuctByProductId(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
