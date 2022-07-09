package com.example.examprepbattleship.repository;

import com.example.examprepbattleship.model.entity.Category;
import com.example.examprepbattleship.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryNameEnum name);
}
