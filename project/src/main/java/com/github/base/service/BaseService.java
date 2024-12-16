package com.github.base.service;

import com.github.base.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseModel<ID>, ID extends Serializable> {
    T save(T t);

    T update(T t);

    Boolean remove(ID id);

    T findById(ID id);

    List<T> findAll();
}
