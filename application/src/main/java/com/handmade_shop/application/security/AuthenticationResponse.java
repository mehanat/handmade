package com.handmade_shop.application.security;

import com.handmade_shop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = -6169509590187531208L;

    private String token;
    private User user;
}
