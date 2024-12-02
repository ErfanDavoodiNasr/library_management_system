package com.github.ui;

import com.github.model.Book;
import com.github.model.Category;
import com.github.model.Subject;
import com.github.util.ApplicationContext;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static com.github.util.Help.*;

public class AdminPage {
    public static void run() {
        while (true) {
            System.out.println("1 - book options");
            System.out.println("2 - category options");
            System.out.println("3 - subject options");
            System.out.println("4 - user options");
            System.out.println("5 - exit");
            System.out.println();
            System.out.println();
            Integer enteredNumber = intInput("choose a option: ");
            if (enteredNumber.equals(null)) {
                System.out.println("please enter valid number");
            } else {
                switch (enteredNumber) {
                    case 1 -> book();
                    case 2 -> category();
                    case 3 -> subject();
                    case 4 -> user();
                    case 5 -> {
                        System.out.println("exiting...");
                        return;
                    }
                }
            }
        }
    }

    private static void user() {

    }

    private static void subject() {

    }

    private static void category() {
        while (true) {
            System.out.println("1 - add category");
            System.out.println("2 - remove category");
            System.out.println("3 - edit category");
            System.out.println("4 - print all category");
            System.out.println("5 - print all category subject");
            System.out.println("6 - print all category books");
            System.out.println("7 - print all available category books");
            System.out.println("8 - exit");
            System.out.println();
            System.out.println();
            Integer enteredNumber = intInput("choose a option: ");
            if (enteredNumber.equals(null)) {
                System.out.println("please enter valid number");
            } else {
                switch (enteredNumber) {
                    case 1 -> saveCategory();
                    case 2 -> removeCategory();
                    case 3 -> updateCategory();
                    case 4 -> findAllCategory();
                    case 5 -> findAllCategorySubject();
                    case 6 -> findAllBooks();
                    case 7 -> FindAllAvailableBooks();
                    case 8 -> {
                        System.out.println("exiting...");
                        return;
                    }
                }
            }
        }
    }

    private static void saveCategory() {
        String enteredName = input("enter a name: ");
        Category category = null;
        try {
            category = ApplicationContext.getCs().save(
                    Category.builder()
                            .title(enteredName)
                            .build()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(category.getTitle().concat(" successfully added"));
    }

    private static void removeCategory() {
        Integer enteredId = intInput("enter a id: ");
        Category category = null;
        try {
            category = ApplicationContext.getCs().findById(enteredId);
        } catch (EntityNotFoundException e) {
            System.out.println("there is no category with this id");
        }
        System.out.println(category.getTitle().concat(" successfully removed"));
    }

    private static void updateCategory() {
        Integer enteredId = intInput("enter a id: ");
        Category category = null;
        try {
            category = ApplicationContext.getCs().findById(enteredId);
            String newTitle = input("enter new title: ");
            category.setTitle(newTitle);
        } catch (EntityNotFoundException e) {
            System.out.println("there is no category with this id");
        }
        System.out.println(category.getTitle().concat(" successfully update"));
    }

    private static void findAllCategory() {
        try {
            List<Category> all = ApplicationContext.getCs().findAll();
            System.out.printf("%-3s %-15s\n", "id", "title");
            for (Category category : all) {
                System.out.printf("%-3s %-15s\n", category.getId(), category.getTitle());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void findAllCategorySubject() {
        try {
            List<Category> all = ApplicationContext.getCs().findAll();
            System.out.printf("%-3s %-15s\n", "id", "title");
            for (Category category : all) {
                System.out.printf("%-3s %-15s\n", category.getSubject().getId(), category.getSubject().getTitle());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void findAllBooks() {
        try {
            List<Book> books = ApplicationContext.getBs().findAll();
            System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", "id", "name", "author", "publisher", "subject");
            for (Book book : books) {
                System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", book.getId(), book.getTitle(), book.getAuthorName(), book.getPublisher(), book.getSubject().getTitle());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void FindAllAvailableBooks() {
        try {
            List<Book> books = ApplicationContext.getBs().findAll();
            System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", "id", "name", "author", "publisher", "subject");
            for (Book book : books) {
                if (book.getIsAvailable()){
                    System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", book.getId(), book.getTitle(), book.getAuthorName(), book.getPublisher(), book.getSubject().getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void book() {
        while (true) {
            System.out.println("1 - add book");
            System.out.println("2 - edit book");
            System.out.println("3 - remove book");
            System.out.println("4 - find book by id");
            System.out.println("5 - search book");
            System.out.println("6 - see all books");
            System.out.println("7 - see all available books");
            System.out.println("8 - exit");
            System.out.println();
            System.out.println();
            Integer enteredNumber = intInput("choose a option: ");
            if (enteredNumber.equals(null)) {
                System.out.println("please enter valid number");
            } else {
                switch (enteredNumber) {
                    case 1 -> saveBook();
                    case 2 -> updateBook();
                    case 3 -> removeBook();
                    case 4 -> findBookById();
                    case 5 -> searchBook();
                    case 6 -> findAllBooks();
                    case 7 -> FindAllAvailableBooks();
                    case 8 -> {
                        System.out.println("exiting...");
                        return;
                    }
                }
            }
        }
    }


    private static void searchBook() {

    }

    private static void findBookById() {

    }

    private static void removeBook() {

    }

    private static void updateBook() {

    }

    private static void saveBook() {
        String enteredName = input("enter a title: ");
        Double price = doubleInput("enter price: ");
        Integer numberOfPages = intInput("enter number of pages: ");
        String publisher = input("enter publisher name: ");
        Book book = null;
        try {
//            book = ApplicationContext.getBs().save(
//                    Book.builder()
//                            .title()
//                            .price()
//                            .numberOfPages()
//                            .publisher()
//                            .subject(
//                                    Subject.builder().id().build()
//                            )
//                            .build()
//            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(book.getTitle().concat(" successfully added"));
    }
}
