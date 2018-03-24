package com.company.webshop.mapper;

import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Item;
import com.company.webshop.dto.ItemDto;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static com.company.webshop.domain.ItemTestBuilder.anItem;
import static com.company.webshop.dto.ItemDtoTestBuilder.anItemDto;
import static java.math.BigDecimal.ROUND_UNNECESSARY;
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

    @Test
    public void toItem_RoundsPriceUp() {
        ItemDto itemDto = anItemDto().withPrice(BigDecimal.valueOf(12.495)).build();

        Item result = itemMapper.toItem(itemDto);

        assertThat(result.getPrice().compareTo(BigDecimal.valueOf(12.5))).isZero();
    }

    @Test
    public void toItem_RoundsPriceDown() {
        ItemDto itemDto = anItemDto().withPrice(BigDecimal.valueOf(12.49499)).build();

        Item result = itemMapper.toItem(itemDto);

        assertThat(result.getPrice().compareTo(BigDecimal.valueOf(12.49))).isZero();
    }

    @Test
    public void toItem_ConvertsPriceToTwoDecimals() {
        ItemDto itemDto = anItemDto().withPrice(BigDecimal.valueOf(12.3)).build();

        Item result = itemMapper.toItem(itemDto);

        assertThat(result.getPrice()).isEqualTo(BigDecimal.valueOf(12.3).setScale(2));
    }

}