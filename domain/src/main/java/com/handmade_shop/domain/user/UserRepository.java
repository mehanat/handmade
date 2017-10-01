package com.handmade_shop.domain.user;

import com.handmade_shop.domain.GenericRepository;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {

    List<User> getAll();

}
