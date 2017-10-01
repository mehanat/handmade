package com.handmade_shop.domain.item;

import com.handmade_shop.domain.BaseEntity;
import lombok.Getter;

public class Item extends BaseEntity {

    @Getter
    private String name;

    @Getter
    private Integer cost;

    @Getter
    private String description;

    @Getter
    private String imageUrl;

}
