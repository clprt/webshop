package com.company.webshop.common.aspects.util;

import com.company.webshop.common.test.UnitTest;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ValidationUtilTest extends UnitTest {

    private static final String MESSAGE_1 = "Message 1";
    private static final String MESSAGE_2 = "Message 2";
    @Mock
    private BindingResult bindingResult;

    @Test
    public void getDefaultMessages() {
        when(bindingResult.getAllErrors()).thenReturn(newArrayList(new ObjectError("ObjectName", MESSAGE_1), new ObjectError("ObjectName", MESSAGE_2)));

        List<String> defaultMessages = ValidationUtil.getDefaultMessages(bindingResult);

        assertThat(defaultMessages).hasSize(2);
        assertThat(defaultMessages).containsExactlyInAnyOrder(MESSAGE_1, MESSAGE_2);
    }
}