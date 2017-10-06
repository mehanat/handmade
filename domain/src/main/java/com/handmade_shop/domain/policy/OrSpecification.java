package com.handmade_shop.domain.policy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class OrSpecification<T> implements Specification<T> {

    private final Specification<T> first;
    private final Specification<T> second;

    public OrSpecification(Specification<T> first,
                            Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T t) {
        return first.isSatisfied(t) || second.isSatisfied(t);
    }

    @Override
    public Predicate toPredicate(CriteriaBuilder cb) {
        return cb.or(first.toPredicate(cb),
                second.toPredicate(cb));
    }
}
