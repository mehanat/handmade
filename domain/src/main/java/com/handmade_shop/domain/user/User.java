package com.handmade_shop.domain.user;

import com.handmade_shop.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

    @Getter
    private String email;

    @Getter
    private String login;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Shop shop;

}
