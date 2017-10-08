package com.handmade_shop.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SessionRepository {

    private final EntityManager em;

    @Autowired
    public SessionRepository(EntityManager em) {
        this.em = em;
    }

    public Session get(String uuid) {
        return em.find(Session.class, uuid);
    }

    public Session save(Session session) {
        if (session.getId() == null) {
            em.persist(session);
        } else {
            em.merge(session);
        }
        return session;
    }
}
