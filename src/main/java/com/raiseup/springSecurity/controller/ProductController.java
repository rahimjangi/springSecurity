package com.raiseup.springSecurity.controller;

import com.raiseup.springSecurity.entity.Product;
import com.raiseup.springSecurity.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products",
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>>getProducts(@RequestParam(name = "page",defaultValue = "0")int page,
                                                    @RequestParam(name = "limit",defaultValue = "10")int limit){
        List<Product> allProducts = productService.getAllProducts(page, limit);
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product>getProduct(@PathVariable("productId")String productId){
        Product product = productService.getProuctByProductId(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product>postProduct(@RequestBody Product product){
        Product savedProduct=productService.save(product);
        return new ResponseEntity<>(savedProduct,HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Product>updateProduct(@RequestBody Product product){
        Product updatedProduct=productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct,HttpStatus.ACCEPTED);
    }
}
