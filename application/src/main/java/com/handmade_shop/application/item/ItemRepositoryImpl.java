package com.handmade_shop.application.item;

import com.handmade_shop.application.GenericRepository;
import com.handmade_shop.domain.item.Item;
import com.handmade_shop.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ItemRepositoryImpl extends GenericRepository<Item> implements ItemRepository {

    @Override
    public Class<Item> getType() {
        return Item.class;
    }

    @Autowired
    public ItemRepositoryImpl(EntityManager em) {
        super(em);
    }

    public Item get(Long id) {
        return em.find(Item.class, id);
    }

}
