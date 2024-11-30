package com.github.ui;

import com.github.model.User;
import com.github.util.ApplicationContext;

import javax.persistence.NoResultException;

import static com.github.util.Help.input;

public class LoginPage {
    public static void login() {
        while (true) {
            String enteredPhoneNumber = input("enter your number(09114905929): ");
            User user = null;
            try {
                user = ApplicationContext.getUs().findByPhoneNUmber(enteredPhoneNumber);
            } catch (NoResultException nre) {
                System.out.println("your phone number not found!!");
                continue;
            }
            switch (user.getRole()) {
                case USER -> UserPage.run();
                case ADMIN -> AdminPage.run();
            }
        }
    }
}
