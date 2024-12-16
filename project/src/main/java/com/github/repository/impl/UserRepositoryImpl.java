package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.User;
import com.github.repository.UserRepository;

import javax.persistence.EntityManager;

import static com.github.util.EntityManagerProvider.getEntityManager;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Integer> implements UserRepository {
    @Override
    public Class<User> getEntityclass() {
        return User.class;
    }

    @Override
    public User update(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            User oldUser = em.find(getEntityclass(), user.getId());
            oldUser.setLastName(user.getLastName());
            oldUser.setRole(user.getRole());
            oldUser.setFirstName(user.getFirstName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setBorrowBooks(user.getBorrowBooks());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return user;
    }
}
