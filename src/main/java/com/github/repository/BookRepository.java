package com.github.repository;

import com.github.base.repository.BaseRepository;
import com.github.model.Book;

import java.util.List;

public interface BookRepository extends BaseRepository<Book, Integer> {
    List<Book> search(String bookTitle);
}
