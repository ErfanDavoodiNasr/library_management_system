package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.Book;
import com.github.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.github.util.EntityManagerProvider.getEntityManager;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book, Integer> implements BookRepository {
    @Override
    public Class<Book> getEntityclass() {
        return Book.class;
    }

    @Override
    public List<Book> search(String bookTitle) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(getEntityclass());
        Root<Book> root = cq.from(getEntityclass());
        cq.select(root).where(cb.like(root.get("title"), "%".concat(bookTitle).concat("%")));
        return em.createQuery(cq).getResultList();
    }
}
