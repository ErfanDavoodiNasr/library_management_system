package com.github.service.impl;

import com.github.base.service.BaseServiceImpl;
import com.github.model.Book;
import com.github.repository.BookRepository;
import com.github.service.BookService;

import java.util.List;
import java.util.function.Predicate;

public class BookServiceImpl extends BaseServiceImpl<Book, Integer, BookRepository> implements BookService {

    public BookServiceImpl(BookRepository bookRepository) {
        super(bookRepository);
    }

    @Override
    public Predicate<Integer> getPredicateId() {
        Predicate<Integer> predicate = (id) -> id.equals(null) || id <= 0;
        return predicate;
    }

    @Override
    public List<Book> search(String title) {
        if (title.equals(null)){
            throw new NullPointerException("you can not search null value");
        }else{
            return getR().search(title);
        }
    }
}
