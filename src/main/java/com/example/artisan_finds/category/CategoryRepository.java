package com.example.artisan_finds.category;


import com.example.artisan_finds.category.entity.Category;
import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.common.repository.GenericSpecificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoriesByCategoryType(CategoryType categoryType);
}
