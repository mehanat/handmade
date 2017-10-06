package com.handmade_shop.domain.user;

import com.handmade_shop.domain.Repository;

import java.util.List;

public interface UserRepository extends Repository<User> {

    List<User> getAll();

}
