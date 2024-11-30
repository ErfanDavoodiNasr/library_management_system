package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.Book;
import com.github.repository.BookRepository;

import javax.persistence.EntityManager;

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
}
