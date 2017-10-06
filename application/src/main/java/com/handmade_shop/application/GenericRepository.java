package com.handmade_shop.application;

import com.handmade_shop.domain.BaseEntity;
import com.handmade_shop.domain.Repository;
import com.handmade_shop.domain.policy.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.List;

public abstract class GenericRepository<ENTITY extends BaseEntity> implements Repository<ENTITY> {

    protected final EntityManager em;

    @Autowired
    public GenericRepository(EntityManager em) {
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

    @Override
    public Class<ENTITY> getType() {
        throw new NotImplementedException();
    }

    @Override
    public List<ENTITY> findBySpecification(Specification<ENTITY> specification) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ENTITY> criteriaQuery = criteriaBuilder.createQuery(getType());
        Predicate predicate = specification.toPredicate(criteriaBuilder);
        criteriaQuery.where(predicate);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
