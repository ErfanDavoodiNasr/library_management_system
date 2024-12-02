package com.github.ui;

import com.github.api.SendSMS;
import com.github.model.User;
import com.github.util.ApplicationContext;
import com.github.util.SecurityContext;
import javax.persistence.NoResultException;

import static com.github.util.Help.input;

public class LoginPage {
    public static void login() {
        while (true){
            try {
                String enteredPhoneNumber = input("enter your number(09114905929): ");
                String[] result = SendSMS.sendSms(enteredPhoneNumber);
                authenticationCode(result);
                User user = null;
                try {
                    user = ApplicationContext.getUs().findByPhoneNUmber(enteredPhoneNumber);
                } catch (NoResultException nre) {
                    user = SignupPage.Signup();
                }
                SecurityContext.setUser(user);
                findRole(user);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void findRole(User user) {
        switch (user.getRole()) {
            case USER -> UserPage.run();
            case ADMIN -> AdminPage.run();
        }
    }

    private static void authenticationCode(String[] result) {
        int counter = 0;
        try{
            while (true) {
                counter++;
                if (counter == 4) {
                    System.out.println("access denied");
                    System.exit(0);
                }
                String code = input("enter the code:");

                if (!result[1].equals("200")) {
                    System.out.println("there is some problem please enter your phone again");
                } else if (!result[0].equals(code)) {
                    System.out.println("wrong code");
                } else {
                    return;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
