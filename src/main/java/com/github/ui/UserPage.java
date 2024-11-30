package com.github.ui;

import com.github.model.Book;
import com.github.util.ApplicationContext;
import com.github.util.SecurityContext;

import java.util.List;

import static com.github.util.Help.intInput;

public class UserPage {
    public static void run(){
        while(true){
            System.out.println("1 - see all books");
            System.out.println("2 - search book");
            System.out.println("3 - see available books");
            System.out.println("4 - borrow books");
            System.out.println("5 - profile");
            System.out.println("6 - exit");
            System.out.println();
            System.out.println();
            Integer enteredNumber = intInput("choose a option: ");
            if (enteredNumber.equals(null)){
                System.out.println("please enter valid number");
            }else{
                switch (enteredNumber){
                    case 1 -> printAllBooks();
                    case 2 -> searchBook();
                    case 3 -> printAvailableBooks();
                    case 4 -> borrowBook();
                    case 5 -> profile();
                    case 6 -> LoginPage.login();
                }
            }

        }
    }

    private static void searchBook() {

    }

    private static void profile() {
    }

    private static void borrowBook() {
        if (SecurityContext.getUser().getBorrowedBook()){
            System.out.println("you have already borrowed a book");
            return;
        }
        Integer bookId = intInput("enter book id");
        Book book = ApplicationContext.getBs().findById(bookId);
        if (book.getIsAvailable() && )

    }

    private static void printAvailableBooks() {
        try{
            List<Book> all = ApplicationContext.getBs().findAll();
            all = all.stream()
                    .filter((book) -> book.getIsAvailable() == Boolean.TRUE)
                    .toList();
            if (all.isEmpty()){
                System.out.println("no book found!!");
            }else{
                System.out.printf("%-3s %-15s %-15s %-15s %-15s\n","id","name","author","publisher","subject");
                for (Book book : all) {
                    System.out.printf("%-3s %-15s %-15s %-15s %-15s\n",book.getId(),book.getTitle(),book.getAuthorName(),book.getPublisher(),book.getSubject().getTitle());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println();
        }
    }

    private static void printAllBooks() {
        try{
            List<Book> all = ApplicationContext.getBs().findAll();
            if (all.isEmpty()){
                System.out.println("no book found!!");
            }else{
                System.out.printf("%-3s %-15s %-15s %-15s %-15s\n","id","name","author","publisher","subject");
                for (Book book : all) {
                    System.out.printf("%-3s %-15s %-15s %-15s %-15s\n",book.getId(),book.getTitle(),book.getAuthorName(),book.getPublisher(),book.getSubject().getTitle());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println();
        }
    }
}
