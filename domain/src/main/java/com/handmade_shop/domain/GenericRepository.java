package com.handmade_shop.domain;

public interface GenericRepository<ENTITY> {

    ENTITY save(ENTITY entity);

    void delete(ENTITY entity);

    ENTITY get(Long id);

}
