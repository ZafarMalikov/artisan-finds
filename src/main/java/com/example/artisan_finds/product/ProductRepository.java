package com.example.artisan_finds.product;

import com.example.artisan_finds.common.repository.GenericSpecificationRepository;
import com.example.artisan_finds.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends GenericSpecificationRepository<Product, Integer> {

}
