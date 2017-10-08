package com.handmade_shop.application.security;

import com.handmade_shop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

public class Session {

    @Getter
    private String id;

    @Getter
    @Setter
    private LocalDateTime expiration;

    @Getter
    private User user;

    public Session(String id, LocalDateTime expiration) {
        this.id = id;
        this.expiration = expiration;
    }

    public boolean isExpired() {
        return this.expiration.isBefore(LocalDateTime.now());
    }

    public static Session init() {
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        LocalDateTime expiration = LocalDateTime.now().plusWeeks(1);
        return new Session(id, expiration);
    }
}
