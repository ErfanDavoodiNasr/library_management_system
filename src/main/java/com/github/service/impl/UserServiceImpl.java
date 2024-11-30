package com.github.service.impl;

import com.github.base.service.BaseServiceImpl;
import com.github.model.User;
import com.github.repository.UserRepository;
import com.github.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

import static com.github.util.EntityManagerProvider.getEntityManager;

public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Predicate<Integer> getPredicateId() {
        Predicate<Integer> predicate = (id) -> id.equals(null) || id <= 0;
        return predicate;
    }

    @Override
    public User findByPhoneNUmber(String phoneNumber) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("phoneNumber"), phoneNumber));
        return em.createQuery(cq).getSingleResult();
    }
}
