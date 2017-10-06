package com.handmade_shop.domain.user;

import com.handmade_shop.domain.BaseEntity;
import com.handmade_shop.domain.item.Item;
import lombok.Getter;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Entity
public class Shop extends BaseEntity {

    @Getter
    private String name;

    @Getter
    private Long rating;

    @Getter
    private Set<Item> items;

}
