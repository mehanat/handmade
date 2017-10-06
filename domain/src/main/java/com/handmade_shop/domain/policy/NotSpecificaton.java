package com.handmade_shop.domain.policy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class NotSpecificaton<T> implements Specification<T> {

    private final Specification<T> specification;

    public NotSpecificaton(Specification<T> specification) {
        this.specification = specification;
    }

    @Override
    public boolean isSatisfied(T t) {
        return !this.specification.isSatisfied(t);
    }

    @Override
    public Predicate toPredicate(CriteriaBuilder cb) {
        return cb.not(this.specification.toPredicate(cb));
    }
}
