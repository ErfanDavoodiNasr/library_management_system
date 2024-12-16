package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.Subject;
import com.github.repository.SubjectRepository;

import javax.persistence.EntityManager;

import static com.github.util.EntityManagerProvider.getEntityManager;

public class SubjectRepositoryImpl extends BaseRepositoryImpl<Subject, Integer> implements SubjectRepository {

    @Override
    public Class<Subject> getEntityclass() {
        return Subject.class;
    }

    @Override
    public Subject update(Subject subject) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Subject oldSubject = em.find(getEntityclass(), subject.getId());
            oldSubject.setCategory(subject.getCategory());
            oldSubject.setTitle(subject.getTitle());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return subject;
    }
}
