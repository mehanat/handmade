package com.handmade_shop.domain.policy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public interface Specification<ENTITY> {
    boolean isSatisfied(ENTITY entity);
    Predicate toPredicate(CriteriaBuilder cb);
}
