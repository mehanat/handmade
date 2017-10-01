package com.handmade_shop.application.item;

import com.handmade_shop.domain.item.Item;
import com.handmade_shop.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item get(Long id) {
        return itemRepository.get(id);
    }

}
