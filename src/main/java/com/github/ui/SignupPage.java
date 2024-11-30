package com.github.ui;

import com.github.SignupException;
import com.github.model.User;
import com.github.util.ApplicationContext;

import static com.github.util.Help.input;

public class SignupPage {
    public static User Signup() {
        String firstName = input("enter your first name: ");
        String lastName = input("enter you last name: ");
        String email = input("enter your email: ");
        String password = input("enter your password: ");
        String phoneNumber = input("enter your phone number: ");
        User user = ApplicationContext.getUs().save(
                User.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .phoneNumber(phoneNumber)
                        .build()
        );

        if (user.getId().equals(null)) {
            throw new SignupException("""
                    there is some problem to signing up you
                    please try again
                    """);
        }
        return user;
    }
}
