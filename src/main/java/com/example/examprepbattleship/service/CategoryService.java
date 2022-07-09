package com.example.examprepbattleship.service;

import com.example.examprepbattleship.model.entity.Category;
import com.example.examprepbattleship.model.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryNameEnum category);
}
