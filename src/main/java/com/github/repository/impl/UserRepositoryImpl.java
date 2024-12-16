package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.User;
import com.github.repository.UserRepository;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Integer> implements UserRepository {
    @Override
    public Class<User> getEntityclass() {
        return User.class;
    }

}
