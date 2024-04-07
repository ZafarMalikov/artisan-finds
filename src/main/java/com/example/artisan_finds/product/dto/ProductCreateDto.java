package com.example.artisan_finds.product.dto;

import com.example.artisan_finds.category.CategoryType;
import com.example.artisan_finds.category.SubCategories;
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
