package com.handmade_shop.application.user;

import com.handmade_shop.application.GenericRepository;
import com.handmade_shop.domain.user.User;
import com.handmade_shop.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericRepository<User> implements UserRepository {

    @Autowired
    public UserRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<User> getType() {
        return User.class;
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

    @Override
    public List<User> getAll() {
        return em.createQuery("FROM User", User.class).getResultList();
    }
}
