package com.github.base.service;

import com.github.base.model.BaseModel;
import com.github.base.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseServiceImpl<T extends BaseModel<ID>, ID extends Serializable, R extends BaseRepository<T, ID>> implements BaseService<T, ID> {
    private final R r;

    public BaseServiceImpl(R r) {
        this.r = r;
    }

    @Override
    public T save(T t) {
        if (t.equals(null)) {
            throw new NullPointerException("you can not persist null value");
        }
        return r.save(t);
    }

    @Override
    public T update(T t) {
        if (t.equals(null)) {
            throw new NullPointerException("you can not update null value");
        } else if (t.getId().equals(null)) {
            throw new NullPointerException("id is null");
        }
        return r.update(t);
    }

    @Override
    public Boolean remove(ID id) {
        isIdValid(id);
        return r.remove(id);
    }

    private void isIdValid(ID id) {
        if (getPredicateId().test(id)) {
            throw new IllegalArgumentException("invalid id");
        }
    }

    @Override
    public T findById(ID id) {
        isIdValid(id);
        return r.findById(id);
    }

    public abstract Predicate<ID> getPredicateId();

    @Override
    public List<T> findAll() {
        List<T> all = r.findAll();
        if (all.equals(null)) {
            throw new NullPointerException("there is no data in database");
        }
        return all;
    }

    public R getR() {
        return r;
    }
}
