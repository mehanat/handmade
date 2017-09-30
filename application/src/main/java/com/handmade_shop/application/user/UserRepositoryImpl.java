package com.handmade_shop.application.user;

import com.handmade_shop.domain.user.User;
import com.handmade_shop.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Autowired
    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User get(Long id) {
        return em.find(User.class, id);
    }
}
