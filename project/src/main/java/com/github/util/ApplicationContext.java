package com.github.util;

import com.github.repository.BookRepository;
import com.github.repository.CategoryRepository;
import com.github.repository.SubjectRepository;
import com.github.repository.UserRepository;
import com.github.repository.impl.BookRepositoryImpl;
import com.github.repository.impl.CategoryRepositoryImpl;
import com.github.repository.impl.SubjectRepositoryImpl;
import com.github.repository.impl.UserRepositoryImpl;
import com.github.service.BookService;
import com.github.service.CategoryService;
import com.github.service.SubjectService;
import com.github.service.UserService;
import com.github.service.impl.BookServiceImpl;
import com.github.service.impl.CategoryServiceImpl;
import com.github.service.impl.SubjectServiceImpl;
import com.github.service.impl.UserServiceImpl;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationContext {
    private final static SubjectRepository sr;
    private final static BookRepository br;
    private final static CategoryRepository cr;
    private final static UserRepository ur;
    @Getter
    private final static SubjectService ss;
    @Getter
    private final static BookService bs;
    @Getter
    private final static CategoryService cs;
    @Getter
    private final static UserService us;

    static {
        sr = new SubjectRepositoryImpl();
        br = new BookRepositoryImpl();
        cr = new CategoryRepositoryImpl();
        ur = new UserRepositoryImpl();
        ss = new SubjectServiceImpl(sr);
        bs = new BookServiceImpl(br);
        cs = new CategoryServiceImpl(cr);
        us = new UserServiceImpl(ur);
    }
}
