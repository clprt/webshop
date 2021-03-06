package com.company.webshop.controller;

import com.company.webshop.application.WebshopApplication;
import com.company.webshop.common.test.ControllerTest;
import com.company.webshop.domain.Item;
import com.company.webshop.dto.ItemDto;
import com.company.webshop.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.ITEM_NAME_ALREADY_IN_USE;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;
import static com.company.webshop.common.aspects.validation.ValidationMessage.FIELD_LENGTH_EXCEEDED;
import static com.company.webshop.common.aspects.validation.ValidationMessage.INVALID_PRICE;
import static com.company.webshop.common.aspects.validation.ValidationMessage.ITEM_NAME_CANNOT_BE_NULL_OR_BLANK;
import static com.company.webshop.domain.ItemTestBuilder.anItem;
import static com.company.webshop.dto.ItemDtoTestBuilder.anItemDto;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WebshopApplication.class)
public class ItemControllerTest extends ControllerTest {

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final BigDecimal PRICE = BigDecimal.valueOf(34.67);
    private static final ItemDto ITEM_DTO = anItemDto()
            .withName(NAME)
            .withDescription(DESCRIPTION)
            .withPrice(PRICE)
            .build();
    private static final Item ITEM = anItem()
            .withName(NAME)
            .withDescription(DESCRIPTION)
            .withPrice(PRICE)
            .build();
    private static final String STRING_256 = StringUtils.repeat("x", 256);

    @Autowired
    private ItemService itemService;

    @Before
    public void setup() throws Exception {
        itemService.deleteAllItems();
    }

    @Test
    public void createItem_AdminPostingValidItemGetsCreatedAndReturnsLocation() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ITEM_DTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/api/items/")));
    }

    @Test
    public void createItem_UserOtherThanAdminGetsForbiddenResponse() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("noAdmin").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(ITEM_DTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void createItem_AdminPostingItemWithItemNameAlreadyInUseReturnsConflictResponseWithIndicativeErrorMessage() throws Exception {
        Item item = itemService.createItem(ITEM);
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto().withName(NAME).build())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error[0]", is(ITEM_NAME_ALREADY_IN_USE.getValue())));
    }

    @Test
    public void createItem_ItemWithNullValuesForItemNameAndPriceReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto()
                        .withName(null)
                        .withPrice(null)
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        ITEM_NAME_CANNOT_BE_NULL_OR_BLANK,
                        INVALID_PRICE
                )));
    }

    @Test
    public void createItem_ItemWithBlankItemNameReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto()
                        .withName("")
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        ITEM_NAME_CANNOT_BE_NULL_OR_BLANK
                )));
    }

    @Test
    public void createItem_ItemWithZeroPriceReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto()
                        .withPrice(BigDecimal.ZERO)
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        INVALID_PRICE
                )));
    }

    @Test
    public void createItem_ItemWithNegativePriceReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto()
                        .withPrice(BigDecimal.valueOf(-12.89))
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        INVALID_PRICE
                )));
    }

    @Test
    public void createItem_ItemWithTooBigPriceReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto()
                        .withPrice(BigDecimal.valueOf(10000000))
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsInAnyOrder(
                        INVALID_PRICE
                )));
    }

    @Test
    public void createItem_ItemWithExceededFieldLengthsReturnsBadRequestResponseWithIndicativeErrorMessages() throws Exception {
        mockMvc.perform(post("/api/items")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(anItemDto()
                        .withName(STRING_256)
                        .withDescription(STRING_256)
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", contains(
                        FIELD_LENGTH_EXCEEDED,
                        FIELD_LENGTH_EXCEEDED
                )));
    }

    @Test
    public void retrieveItem_ItemExistsReturnsItem() throws Exception {
        Item item = itemService.createItem(ITEM);
        mockMvc.perform(get("/api/items/" + item.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.itemId", is(item.getId().intValue())))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)))
                .andExpect(jsonPath("$.price", is(PRICE.doubleValue())));
    }

    @Test
    public void retrieveItem_ItemDoesntExistReturnsResourceNotFoundResponse() throws Exception {
        mockMvc.perform(get("/api/items/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error[0]", is(RESOURCE_NOT_FOUND.getValue())));
    }
}