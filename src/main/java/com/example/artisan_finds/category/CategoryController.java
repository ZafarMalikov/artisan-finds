package com.example.artisan_finds.category;

import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import com.example.artisan_finds.category.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public  class CategoryController {

private final CategoryService categoryService;
//todo base categoryni chiqarishni qilish
    //todo yuborilgan subkategoryni productlarni chiqarish
    //todo product bilan categorylarni bog'lash
    @GetMapping("/first/categoryType")
    public ResponseEntity<List<SubCategory>> category(){
        List<SubCategory> subCategory = categoryService.getSubCategory("ACCESSORIES");
        return ResponseEntity.ok(subCategory);
    }
}
