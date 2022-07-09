package com.example.examprepbattleship.service.impl;

import com.example.examprepbattleship.model.entity.Category;
import com.example.examprepbattleship.model.enums.CategoryNameEnum;
import com.example.examprepbattleship.repository.CategoryRepository;
import com.example.examprepbattleship.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if(categoryRepository.count() > 0){
            return;
        }
        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();
                    category.setName(categoryNameEnum);
                    categoryRepository.save(category);
                });
    }

    @Override
    public Category findByCategoryName(CategoryNameEnum category) {
        return categoryRepository.findByName(category);
    }
}
