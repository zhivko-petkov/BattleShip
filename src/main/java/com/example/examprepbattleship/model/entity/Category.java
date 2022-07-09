package com.example.examprepbattleship.model.entity;

import com.example.examprepbattleship.model.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CategoryNameEnum name;

    @Column(nullable = true, columnDefinition = "text")
    private String description;

    public Category() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
