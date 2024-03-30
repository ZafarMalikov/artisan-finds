package com.example.artisan_finds.product.dto;

import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private String name;
    private Integer productCount;
    private Double price;
    private String info;
    private CategoryType categoryType;
    private SubCategories subCategories;
    private Integer userId;
}
