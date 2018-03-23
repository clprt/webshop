package com.company.webshop.service;

import com.company.webshop.common.aspects.exception.NotUniqueWebShopException;
import com.company.webshop.common.aspects.exception.ResourceNotFoundWebshopException;
import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Item;
import com.company.webshop.repository.ItemRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.ITEM_NAME_ALREADY_IN_USE;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;
import static com.company.webshop.domain.ItemTestBuilder.anItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemServiceImplementationTest extends UnitTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final BigDecimal PRICE = BigDecimal.valueOf(34.67);
    private static final Item ITEM = anItem()
            .withName(NAME)
            .withDescription(DESCRIPTION)
            .withPrice(PRICE)
            .build();
    private static final String ALREADY_EXISTING_ITEM_NAME = "allreadyExistingItemName";
    private static final Item ITEM_WITH_ALREADY_EXISTING_ITEM_NAME = anItem()
            .withName(ALREADY_EXISTING_ITEM_NAME)
            .build();
    private static final String NOT_YET_EXISTING_ITEM_NAME = "notYetExistingItemName";
    private static final Item ITEM_WITH_NOT_YET_EXISTING_ITEM_NAME = anItem()
            .withName(NOT_YET_EXISTING_ITEM_NAME)
            .build();


    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImplementation itemService;

    @Test
    public void getItemById_WhenItemExistsReturnsItem() {
        when(itemRepository.findById(ID)).thenReturn(Optional.of(ITEM));

        Item result = itemService.getItemById(ID);

        assertThat(result).isEqualTo(ITEM);
    }

    @Test
    public void getItemById_WhenItemDoesNotExistThrowsResourceNotFoundWebshopException() {
        when(itemRepository.findById(ID)).thenReturn(Optional.empty());
        expectedException.expect(ResourceNotFoundWebshopException.class);
        expectedException.expectMessage(RESOURCE_NOT_FOUND.getValue());

        itemService.getItemById(ID);
    }

    @Test
    public void createItem_ReturnsCreatedItem() {
        Item item = anItem()
                .withId(ID)
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withPrice(PRICE)
                .build();
        when(itemRepository.save(ITEM)).thenReturn(item);

        Item result = itemService.createItem(ITEM);

        assertThat(result).isEqualTo(item);
    }

    @Test
    public void validateItemNameIsUnique__AllowsNotYetExistingItemName() {
        when(itemRepository.findByName(NOT_YET_EXISTING_ITEM_NAME)).thenReturn(Optional.empty());

        itemService.validateItemNameIsUnique(ITEM_WITH_NOT_YET_EXISTING_ITEM_NAME);
    }

    @Test
    public void validateItemNameIsUnique_ThrowsNotUniqueWebshopExceptionWithMessageItemNameAlreadyInUse() throws Exception {
        when(itemRepository.findByName(ALREADY_EXISTING_ITEM_NAME)).thenReturn(Optional.of(ITEM));
        expectedException.expect(NotUniqueWebShopException.class);
        expectedException.expectMessage(ITEM_NAME_ALREADY_IN_USE.getValue());

        itemService.validateItemNameIsUnique(ITEM_WITH_ALREADY_EXISTING_ITEM_NAME);
    }

    @Test
    public void deleteAllItems_CallsDeleteAllInBatchOnTheItemRepository() {
        itemService.deleteAllItems();

        verify(itemRepository).deleteAllInBatch();
    }

}