package com.example.artisan_finds.product;


import com.example.artisan_finds.category.CategoryType;
import com.example.artisan_finds.category.SubCategories;
import com.example.artisan_finds.product.dto.ProductCreateDto;
import com.example.artisan_finds.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/product-by-type")
    public ResponseEntity<List<ProductResponseDto>> getAllProductByBaseCategory(
            @RequestParam CategoryType categoryType
    ) {
        List<ProductResponseDto> allByType = productService.getAllByType(categoryType);

        return ResponseEntity.ok(allByType);
    }


    @GetMapping("/sub-categories")
    public ResponseEntity<List<ProductResponseDto>>getAllProductBySubCategories(@RequestParam SubCategories subCategories){
        List<ProductResponseDto> allBySubCategories = productService.getAllBySubCategories(subCategories);
        return ResponseEntity.ok(allBySubCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto>getById(@PathVariable("id")Integer id) {
        ProductResponseDto byId = productService.getById(id);
        return ResponseEntity.ok(byId);
    }


}
