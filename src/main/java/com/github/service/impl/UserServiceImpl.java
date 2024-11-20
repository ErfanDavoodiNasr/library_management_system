package com.github.service.impl;

import com.github.base.BaseServiceImpl;
import com.github.model.User;
import com.github.repository.UserRepository;
import com.github.service.UserService;

import java.util.function.Predicate;

public class UserServiceImpl extends BaseServiceImpl<User,Integer, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Predicate<Integer> getPredicateId() {
        Predicate<Integer> predicate = (id) -> id.equals(null) || id <= 0;
        return predicate;
    }
}
