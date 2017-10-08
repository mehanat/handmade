package com.handmade_shop.application.security;

import com.handmade_shop.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorizedUserService {
    private final ServletContext servletContext;

    @Autowired
    public AuthorizedUserService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private User safeGet() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(u -> u instanceof User)
                .map(u -> (User) u)
                .orElse(null);
    }

    public User get() {
        return safeGet();
    }

}
