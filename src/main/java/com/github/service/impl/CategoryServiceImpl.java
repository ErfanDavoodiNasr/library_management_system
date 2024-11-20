package com.github.service.impl;

import com.github.base.service.BaseServiceImpl;
import com.github.model.Category;
import com.github.repository.CategoryRepository;
import com.github.service.CategoryService;

import java.util.function.Predicate;

public class CategoryServiceImpl extends BaseServiceImpl<Category,Integer, CategoryRepository> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    @Override
    public Predicate<Integer> getPredicateId() {
        Predicate<Integer> predicate = (id) -> id.equals(null) || id <= 0;
        return predicate;
    }
}
