package com.handmade_shop.application.security;


import com.handmade_shop.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static org.springframework.util.StringUtils.isEmpty;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final SessionRepository sessionRepository;
    private final UserDetailsService userDetailsService;

    public AuthenticationTokenFilter(SessionRepository sessionRepository,
                                     UserDetailsService userDetailsService) {
        this.sessionRepository = sessionRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String headerToken = request.getHeader("X-Auth-Token");
        String paramToken = request.getParameter("token");

        String token = Objects.isNull(headerToken)
                ? paramToken
                : headerToken;

        if (Objects.nonNull(token)) {
            Session session = sessionRepository.get(token);
            if (Objects.nonNull(session) && !session.isExpired()) {
                String username = session.getUser().getLogin();
                User user = getAuthorizedUserByUsername(username, request);
                if (Objects.nonNull(user)) {
                    setUserToContext(user, session);
                }
            }
        }

        chain.doFilter(req, res);
    }

    private User getAuthorizedUserByUsername(String username, HttpServletRequest request) {
        try {
            return (User) userDetailsService.loadUserByUsername(username);
        } catch (AuthenticationException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return null;
        }
    }

    private void setUserToContext(User user, Session session) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    private Session injectSession(String username) {
        //не смог тут использовать @InjectSession добавил руками
        Session session = Session.init();
        return sessionRepository.save(session);
    }

    private String getUserNameFromOpenAmUUID(String payload) {
        return Arrays.stream(payload.split(","))
                .map(keyValuePairs -> keyValuePairs.split("="))
                .filter(keyValue -> "uid".equals(keyValue[0]))
                .findAny()
                .map(keyValue -> keyValue[1])
                .orElse(null);
    }
}
