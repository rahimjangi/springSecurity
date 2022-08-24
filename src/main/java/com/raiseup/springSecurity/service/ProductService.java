package com.raiseup.springSecurity.service;


import com.raiseup.springSecurity.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product getProuctByProductId(String productId);
}
