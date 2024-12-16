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
    public Book update(Book book) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Book oldBook = em.find(getEntityclass(), book.getId());
            oldBook.setPublisher(book.getPublisher());
            oldBook.setWeight(book.getWeight());
            oldBook.setPrice(book.getPrice());
            oldBook.setTitle(book.getTitle());
            oldBook.setSubject(book.getSubject());
            oldBook.setNumberOfPages(book.getNumberOfPages());
            oldBook.setAuthorName(book.getAuthorName());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return book;
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
