package com.github.util;

import com.github.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityContext {
    @Getter
    @Setter
    private static User user;
}
