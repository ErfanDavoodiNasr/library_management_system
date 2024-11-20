package com.github.service.impl;

import com.github.base.BaseServiceImpl;
import com.github.model.Subject;
import com.github.repository.SubjectRepository;
import com.github.service.SubjectService;

import java.util.function.Predicate;

public class SubjectServiceImpl extends BaseServiceImpl<Subject,Integer, SubjectRepository> implements SubjectService {

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        super(subjectRepository);
    }

    @Override
    public Predicate<Integer> getPredicateId() {
        Predicate<Integer> predicate = (id) -> id.equals(null) || id <= 0;
        return predicate;
    }
}
