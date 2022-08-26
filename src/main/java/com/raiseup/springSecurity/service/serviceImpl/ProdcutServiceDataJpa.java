package com.raiseup.springSecurity.service.serviceImpl;

import com.raiseup.springSecurity.entity.Product;
import com.raiseup.springSecurity.repository.ProductRepository;
import com.raiseup.springSecurity.service.ProductService;
import com.raiseup.springSecurity.utils.StringIdBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Product> getAllProducts(int page, int limit) {
        Pageable pageable= PageRequest.of(page, limit);
        List<Product> productList = productRepository.findAll(pageable).getContent();
        return productList;
    }

    @Override
    public Product save(Product product) {
        product.setProductId(StringIdBuilder.buildID(30));
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(productRepository.findByProductId(product.getProductId()).isEmpty())throw new IllegalStateException("Product does not exists");
        return productRepository.save(product);
    }
}
