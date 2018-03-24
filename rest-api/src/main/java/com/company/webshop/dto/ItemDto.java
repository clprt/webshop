package com.company.webshop.dto;

import com.company.webshop.common.aspects.ddd.ValueObject;
import com.company.webshop.common.aspects.validation.Length;
import com.company.webshop.common.aspects.validation.Price;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

import static com.company.webshop.common.aspects.validation.ValidationMessage.ITEM_NAME_CANNOT_BE_NULL_OR_BLANK;

public class ItemDto extends ValueObject {

    private Long itemId;
    @Length
    @NotBlank(message = ITEM_NAME_CANNOT_BE_NULL_OR_BLANK)
    private String name;
    @Length
    private String description;
    @Price(value = 7)
    private BigDecimal price;

    private ItemDto() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class ItemDtoBuilder {

        private ItemDto itemDto;

        private ItemDtoBuilder() {
            this.itemDto = new ItemDto();
        }

        public static ItemDtoBuilder itemDto() {
            return new ItemDtoBuilder();
        }

        public ItemDto build() {
            return this.itemDto;
        }

        public ItemDtoBuilder withItemId(Long itemId) {
            this.itemDto.setItemId(itemId);
            return this;
        }

        public ItemDtoBuilder withName(String name) {
            this.itemDto.setName(name);
            return this;
        }

        public ItemDtoBuilder withDescription(String description) {
            this.itemDto.setDescription(description);
            return this;
        }

        public ItemDtoBuilder withPrice(BigDecimal price) {
            this.itemDto.setPrice(price);
            return this;
        }
    }
}
