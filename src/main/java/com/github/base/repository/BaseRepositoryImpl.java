package com.github.base.repository;


import com.github.base.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static com.github.util.EntityManagerProvider.getEntityManager;

public abstract class BaseRepositoryImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseRepository<T, ID> {
    @Override
    public Boolean remove(ID id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T t = em.find(getEntityclass(), id);
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return Boolean.FALSE;
        } finally {
            em.close();
        }
        return Boolean.TRUE;
    }

    public abstract Class<T> getEntityclass();

    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityclass());
        Root<T> all = cq.from(getEntityclass());
        cq.select(all);
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public T findById(ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(getEntityclass(), id);
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public T save(T t) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t);
            } else {
                em.merge(t);
            }
            em.getTransaction().commit();
            return t;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
