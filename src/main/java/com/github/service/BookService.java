package com.github.service;

import com.github.base.service.BaseService;
import com.github.model.Book;

import java.util.List;

public interface BookService extends BaseService<Book, Integer> {
    List<Book> search(String title);
}
