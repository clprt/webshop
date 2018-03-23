package com.company.webshop.service;

import com.company.webshop.domain.Item;

public interface ItemService {

    Item getItemById(Long id);

    Item createItem(Item item);

    void deleteAllItems();

    void validateItemNameIsUnique(Item item);
}
