package com.handmade_shop.domain;

import com.handmade_shop.domain.policy.Specification;

import java.util.List;

public interface Repository<ENTITY> {

    ENTITY save(ENTITY entity);

    void delete(ENTITY entity);

    ENTITY get(Long id);

    List<ENTITY> findBySpecification(Specification<ENTITY> specification);

    Class<ENTITY> getType();

}
