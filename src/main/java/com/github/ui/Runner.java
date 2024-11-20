package com.github.ui;

import com.github.model.Subject;
import com.github.util.ApplicationContext;
import java.util.List;

public class Runner {

    public static void printAllNotEmptySubject(){
        List<Subject> all = ApplicationContext.getSs().findAll();
        all = all.stream()
                .filter((s) -> !s.getBooks().isEmpty())
                .toList();
        System.out.println(all);
    }

    public static void printAllSubject(){
        List<Subject> all = ApplicationContext.getSs().findAll();
        System.out.println(all);
    }
}
