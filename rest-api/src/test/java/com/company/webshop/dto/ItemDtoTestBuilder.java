package com.company.webshop.dto;

import java.math.BigDecimal;

import static com.company.webshop.dto.ItemDto.ItemDtoBuilder.itemDto;

public class ItemDtoTestBuilder {

    private Long itemId;
    private String name = "name";
    private String description = "description";
    private BigDecimal price = BigDecimal.valueOf(123.45);

    private ItemDtoTestBuilder() {
    }

    public static ItemDtoTestBuilder anItemDto() {
        return new ItemDtoTestBuilder();
    }

    public ItemDto build() {
        return itemDto()
                .withItemId(itemId)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .build();
    }

    public ItemDtoTestBuilder withItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemDtoTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemDtoTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemDtoTestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
