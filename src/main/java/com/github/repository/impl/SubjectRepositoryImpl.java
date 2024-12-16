package com.github.repository.impl;

import com.github.base.repository.BaseRepositoryImpl;
import com.github.model.Subject;
import com.github.repository.SubjectRepository;

public class SubjectRepositoryImpl extends BaseRepositoryImpl<Subject, Integer> implements SubjectRepository {

    @Override
    public Class<Subject> getEntityclass() {
        return Subject.class;
    }

}
