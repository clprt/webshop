package com.company.webshop.controller;

import com.company.webshop.domain.Item;
import com.company.webshop.dto.ItemDto;
import com.company.webshop.mapper.ItemMapper;
import com.company.webshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto) {
        Item item = itemMapper.toItem(itemDto);
        itemService.validateItemNameIsUnique(item);
        Item savedItem = itemService.createItem(item);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ItemDto> retrieveItem(@PathVariable("id") Long id) {
        ItemDto itemDto = itemMapper.toItemDto(itemService.getItemById(id));
        return ResponseEntity.ok(itemDto);
    }
}
