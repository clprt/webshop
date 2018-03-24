package com.company.webshop.mapper;

import com.company.webshop.domain.Item;
import com.company.webshop.dto.ItemDto;
import org.springframework.stereotype.Component;

import static com.company.webshop.domain.Item.ItemBuilder.item;
import static com.company.webshop.dto.ItemDto.ItemDtoBuilder.itemDto;
import static java.math.BigDecimal.ROUND_HALF_UP;

@Component
public class ItemMapper {

    public Item toItem(ItemDto itemDto) {
        return item()
                .withName(itemDto.getName())
                .withDescription(itemDto.getDescription())
                .withPrice(itemDto.getPrice().setScale(2, ROUND_HALF_UP))
                .build();
    }

    public ItemDto toItemDto(Item item) {
        return itemDto()
                .withItemId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .withPrice(item.getPrice())
                .build();
    }
}
