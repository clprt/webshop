package com.company.webshop.mapper;

import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Item;
import com.company.webshop.dto.ItemDto;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static com.company.webshop.domain.ItemTestBuilder.anItem;
import static com.company.webshop.dto.ItemDtoTestBuilder.anItemDto;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemMapperTest extends UnitTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final BigDecimal PRICE = BigDecimal.valueOf(56.98);
    private static final ItemDto ITEM_DTO = anItemDto()
            .withName(NAME)
            .withDescription(DESCRIPTION)
            .withPrice(PRICE)
            .build();
    private static final Item ITEM_PERSISTED = anItem()
            .withId(ID)
            .withName(NAME)
            .withDescription(DESCRIPTION)
            .withPrice(PRICE)
            .build();

    @InjectMocks
    private ItemMapper itemMapper;

    @Test
    public void toItem_MapsItemDtoToItem() {
        Item item = itemMapper.toItem(ITEM_DTO);

        Item expected = anItem()
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withPrice(PRICE)
                .build();
        assertThat(item).isEqualTo(expected);
    }

    @Test
    public void toItemDto_MapsItemToItemDto() {
        ItemDto itemDto = itemMapper.toItemDto(ITEM_PERSISTED);

        ItemDto expected = anItemDto()
                .withItemId(ID)
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withPrice(PRICE)
                .build();
        assertThat(itemDto).isEqualTo(expected);
    }
}