package com.company.webshop.controller;

import com.company.webshop.common.test.UnitTest;
import com.company.webshop.domain.Item;
import com.company.webshop.dto.ItemDto;
import com.company.webshop.mapper.ItemMapper;
import com.company.webshop.service.ItemService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.company.webshop.domain.ItemTestBuilder.anItem;
import static com.company.webshop.dto.ItemDtoTestBuilder.anItemDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ItemControllerUnitTest extends UnitTest {

    private static final ItemDto ITEM_DTO = anItemDto().build();
    private static final String NAME = "name";
    private static final Item ITEM = anItem().withName(NAME).build();
    private static final long ID = 1L;
    private static final Item PERSISTED_ITEM = anItem().withId(ID).build();

    @Mock
    private ItemMapper itemMapper;
    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    public void createItem_ReturnsResponseEntityWithCorrectUriLocation() {
        when(itemMapper.toItem(ITEM_DTO)).thenReturn(ITEM);
        when(itemService.createItem(ITEM)).thenReturn(PERSISTED_ITEM);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(8080);
        request.setRequestURI("/api/items");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<?> responseEntity = itemController.createItem(ITEM_DTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation().toString()).isEqualTo(request.getRequestURL() + "/" + ID);
    }
}