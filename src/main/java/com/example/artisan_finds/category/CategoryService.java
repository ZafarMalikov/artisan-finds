package com.example.artisan_finds.category;

import com.example.artisan_finds.category.entity.Category;
import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import com.example.artisan_finds.category.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    public List<SubCategory>getSubCategory(String subCategoryType){
        CategoryType categoryType = CategoryType.valueOf(subCategoryType);
        Category category = categoryRepository.findCategoriesByCategoryType(categoryType);

        List<SubCategory> byCategoryId = subCategoryRepository.findAllByCategoryId(1);
        return byCategoryId;

    }
}
