package com.github.ui;

import com.github.model.Book;
import com.github.model.User;
import com.github.util.ApplicationContext;
import com.github.util.SecurityContext;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.github.util.Help.input;
import static com.github.util.Help.intInput;

public class UserPage {
    public static void run() {
        while (true) {
            System.out.println("1 - see all books");
            System.out.println("2 - search book");
            System.out.println("3 - see available books");
            System.out.println("4 - borrow books");
            System.out.println("5 - profile");
            System.out.println("6 - exit");
            System.out.println();
            System.out.println();
            Integer enteredNumber = intInput("choose a option: ");
            if (enteredNumber.equals(null)) {
                System.out.println("please enter valid number");
            } else {
                switch (enteredNumber) {
                    case 1 -> printAllBooks();
                    case 2 -> searchBook();
                    case 3 -> printAvailableBooks();
                    case 4 -> borrowBook();
                    case 5 -> profile();
                    case 6 -> {
                        System.out.println("exiting...");
                        return;
                    }
                }
            }

        }
    }

    private static void searchBook() {
        try{
            String enteredTitle = input("enter book title: ");
            List<Book> books = ApplicationContext.getBs().search(enteredTitle);
            if (books.isEmpty()){
                System.out.println("no result found");
            }else{
                System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", "id", "name", "author", "publisher", "subject");
                for (Book book : books) {
                    System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", book.getId(), book.getTitle(), book.getAuthorName(), book.getPublisher(), book.getSubject().getTitle());
                }
            }
        }catch (EntityNotFoundException e){
            System.out.println("no result found");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void profile() {
        User user = SecurityContext.getUser();
        System.out.printf("%-3s %-20s %-30s %-18s %-13s\n", "id", "full name", "email", "password", "phone");
        System.out.printf("%-3s %-20s %-30s %-18s %-13s\n",
                user.getId(),
                user.getFirstName().concat(" ").concat(user.getLastName()),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber());
        if (user.getBorrowBooks().isEmpty()){
            System.out.println("you have not borrowed any book");
        }else{
            System.out.println("borrowed books: ");
            System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", "id", "name", "author", "publisher", "subject");
            for (Book book : user.getBorrowBooks()) {
                System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", book.getId(), book.getTitle(), book.getAuthorName(), book.getPublisher(), book.getSubject().getTitle());
            }
        }
    }

    private static void borrowBook() {
        try {
            Integer bookId = intInput("enter book id: ");
            Book book = ApplicationContext.getBs().findById(bookId);
            if (book.getIsAvailable()) {
                User user = SecurityContext.getUser();
                user.getBorrowBooks().add(book);
                User updatedUser = ApplicationContext.getUs().update(user);
                if (updatedUser.getBorrowBooks().contains(book)){
                    String username = user.getFirstName().concat(" ").concat(user.getLastName());
                    System.out.println(book.getTitle().concat(" successfully borrowed by ".concat(username)));
                }else{
                    System.out.println("there is some problem");
                }
            }else{
                System.out.println("book is not available");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printAvailableBooks() {
        try {
            List<Book> all = ApplicationContext.getBs().findAll();
            all = all.stream()
                    .filter((book) -> book.getIsAvailable() == Boolean.TRUE)
                    .toList();
            bookListIsEmpty(all);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println();
        }
    }

    private static void printAllBooks() {
        try {
            List<Book> all = ApplicationContext.getBs().findAll();
            bookListIsEmpty(all);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println();
        }
    }

    private static void bookListIsEmpty(List<Book> all) {
        if (all.isEmpty()) {
            System.out.println("no book found!!");
        } else {
            System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", "id", "name", "author", "publisher", "subject");
            for (Book book : all) {
                System.out.printf("%-3s %-15s %-15s %-15s %-15s\n", book.getId(), book.getTitle(), book.getAuthorName(), book.getPublisher(), book.getSubject().getTitle());
            }
        }
    }
}
