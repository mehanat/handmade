package com.handmade_shop.domain;

import java.util.List;

public interface GenericRepository<ENTITY> {

    ENTITY save(ENTITY entity);

    void delete(ENTITY entity);

    ENTITY get(Long id);

}
