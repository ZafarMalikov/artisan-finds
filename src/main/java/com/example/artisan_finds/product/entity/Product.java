package com.example.artisan_finds.product.entity;

import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import com.example.artisan_finds.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer productCount;
    private Double price;
    private String info;
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    @Enumerated(EnumType.STRING)
    private SubCategories subCategories;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
