package com.raiseup.springSecurity.service.serviceImpl;

import com.raiseup.springSecurity.entity.Product;
import com.raiseup.springSecurity.repository.ProductRepository;
import com.raiseup.springSecurity.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProdcutServiceDataJpa implements ProductService {
    private final ProductRepository productRepository;

    public ProdcutServiceDataJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProuctByProductId(String productId) {
        return productRepository.findByProductId(productId).orElse(null);
    }
}
