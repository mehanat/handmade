package com.handmade_shop.application;

import com.handmade_shop.domain.BaseEntity;
import com.handmade_shop.domain.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class GenericRepositoryImpl<ENTITY extends BaseEntity> implements GenericRepository<ENTITY> {

    protected final EntityManager em;

    @Autowired
    public GenericRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public ENTITY save(ENTITY entity) {
        if (entity.isNew()) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Override
    public void delete(ENTITY entity) {
        em.remove(entity);
    }

    @Override
    public abstract ENTITY get(Long id);

}
