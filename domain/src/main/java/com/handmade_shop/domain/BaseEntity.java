package com.handmade_shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

public class BaseEntity {

    @Getter
    protected Long id;

    @JsonIgnore
    public boolean isNew() {
        return id ==  null;
    }

}
