package com.company.webshop.domain;

import java.math.BigDecimal;

import static com.company.webshop.domain.Item.ItemBuilder.item;

public class ItemTestBuilder {

    private Long id;
    private String name = "name";
    private String description = "description";
    private BigDecimal price = BigDecimal.valueOf(123.45);

    private ItemTestBuilder() {
    }

    public static ItemTestBuilder anItem() {
        return new ItemTestBuilder();
    }

    public Item build() {
        return item()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .build();
    }

    public ItemTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemTestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
