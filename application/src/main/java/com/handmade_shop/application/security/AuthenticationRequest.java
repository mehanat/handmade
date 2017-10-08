package com.handmade_shop.application.security;

import lombok.Getter;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -8878440863852093557L;

    @Getter
    private String username;
    @Getter
    private String password;
}
