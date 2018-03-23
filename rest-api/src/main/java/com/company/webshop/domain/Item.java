package com.company.webshop.domain;

import com.company.webshop.common.aspects.ddd.ValueObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends ValueObject {

    public static final String TABLE = "item";
    public static final String ID_COLUMN = TABLE + "_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    private Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static class ItemBuilder {

        private Item item;

        private ItemBuilder() {
            this.item = new Item();
        }

        public static ItemBuilder item() {
            return new ItemBuilder();
        }

        public Item build() {
            return this.item;
        }

        public ItemBuilder withId(Long id) {
            this.item.setId(id);
            return this;
        }

        public ItemBuilder withName(String name) {
            this.item.setName(name);
            return this;
        }

        public ItemBuilder withDescription(String description) {
            this.item.setDescription(description);
            return this;
        }

        public ItemBuilder withPrice(BigDecimal price) {
            this.item.setPrice(price);
            return this;
        }
    }
}
