package com.raiseup.springSecurity.service;


import com.raiseup.springSecurity.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProuctByProductId(String productId);
    List<Product> getAllProducts(int page, int limit);

    Product save(Product product);

    Product updateProduct(Product product);
}
