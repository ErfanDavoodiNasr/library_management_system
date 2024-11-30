package com.github.ui;

import com.github.model.Category;
import com.github.model.Subject;
import com.github.util.ApplicationContext;

import java.util.List;

import static com.github.util.Help.input;
import static com.github.util.Help.intInput;

public class Runner {

    public static void printAllNotEmptySubject() {
        List<Subject> all = ApplicationContext.getSs().findAll();
        all = all.stream()
                .filter((s) -> !s.getBooks().isEmpty())
                .toList();
        System.out.println(all);
    }

    public static void printAllSubject() {
        List<Subject> all = ApplicationContext.getSs().findAll();
        System.out.println(all);
    }

    public static void saveCategory() {
        try {
            String input = input("enter a name: ");
            Category save = ApplicationContext.getCs().save(Category.builder().title(input).build());
            if (save.getId() != null) {
                System.out.println("category with title " + input + " and id " + save.getId() + " created");
            } else {
                System.out.println("""
                        there is some problem to creating category
                        please try again...
                        """);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeCategory() {
        List<Category> all = ApplicationContext.getCs().findAll();
        printAllCategories(all);

        Integer input = intInput("enter an id: ");
        if (ApplicationContext.getCs().remove(input)) {
            System.out.println("category successfully removed");
        } else {
            System.out.println("""
                    there is some problem to removing category
                    please try again...
                    """);
        }
    }

    private static void printAllCategories(List<Category> all) {
        System.out.printf("%-5s %-15s\n", "id", "title");
        for (Category category : all) {
            System.out.printf("%-5s %-15s\n", category.getId(), category.getTitle());
        }
    }
}
