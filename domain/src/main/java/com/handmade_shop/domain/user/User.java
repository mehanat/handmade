package com.handmade_shop.domain.user;

public class User {

    private Long id;

    public boolean isNew() {
        return id == null;
    }
}
