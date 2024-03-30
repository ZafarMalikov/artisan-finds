package com.example.artisan_finds.product;


import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import com.example.artisan_finds.product.dto.ProductCreateDto;
import com.example.artisan_finds.product.dto.ProductResponseDto;
import com.example.artisan_finds.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponseDto>create(@RequestBody ProductCreateDto productCreateDto){
        ProductResponseDto productResponseDto = productService.create(productCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productResponseDto);
    }


    @GetMapping("/type")
    public ResponseEntity<List<ProductResponseDto>> getAllProductByType(
            @RequestParam CategoryType categoryType,
            Pageable pageable,
            @RequestParam(required = false) String predicate
    ) {
        List<ProductResponseDto> allByType = productService.getAllByType(categoryType, pageable, predicate);

        return ResponseEntity.ok(allByType);
    }


    @GetMapping("/sub-categories")
    public ResponseEntity<List<ProductResponseDto>>getAllProductBySubCategories(@RequestParam SubCategories subCategories){
        List<ProductResponseDto> allBySubCategories = productService.getAllBySubCategories(subCategories);
        return ResponseEntity.ok(allBySubCategories);
    }


}
