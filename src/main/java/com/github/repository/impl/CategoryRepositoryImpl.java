package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.Category;
import com.github.repository.CategoryRepository;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, Integer> implements CategoryRepository {
    @Override
    public Class<Category> getEntityclass() {
        return Category.class;
    }
}
