package com.raiseup.springSecurity.repository;

import com.raiseup.springSecurity.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByProductId(String productId);
}
