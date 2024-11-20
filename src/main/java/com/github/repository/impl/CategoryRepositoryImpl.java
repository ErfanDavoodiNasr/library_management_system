package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.Category;
import com.github.repository.CategoryRepository;

import javax.persistence.EntityManager;

import static com.github.util.EntityManagerProvider.getEntityManager;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category,Integer> implements CategoryRepository {
    @Override
    public Class<Category> getEntityclass() {
        return Category.class;
    }

    @Override
    public Category update(Category category) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Category oldCategory = em.find(getEntityclass(), category.getId());
            oldCategory.setTitle(category.getTitle());
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        return category;
    }
}
