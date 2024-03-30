package com.example.artisan_finds.product.dto;

import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import com.example.artisan_finds.product.entity.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {

    private String name;
    private Integer productCount;
    private Double price;
    private String info;
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    @Enumerated(EnumType.STRING)
    private SubCategories subCategories;
}
