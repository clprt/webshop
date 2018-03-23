package com.company.webshop.service;

import com.company.webshop.common.aspects.exception.NotUniqueWebShopException;
import com.company.webshop.common.aspects.exception.ResourceNotFoundWebshopException;
import com.company.webshop.domain.Item;
import com.company.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.ITEM_NAME_ALREADY_IN_USE;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;

@Service
public class ItemServiceImplementation implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item getItemById(Long id) {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundWebshopException(RESOURCE_NOT_FOUND));
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteAllItems() {
        itemRepository.deleteAllInBatch();
    }

    @Override
    public void validateItemNameIsUnique(Item item) {
        itemRepository.findByName(item.getName())
                .ifPresent(anItem -> {
                    throw new NotUniqueWebShopException(ITEM_NAME_ALREADY_IN_USE);
                });
    }
}
