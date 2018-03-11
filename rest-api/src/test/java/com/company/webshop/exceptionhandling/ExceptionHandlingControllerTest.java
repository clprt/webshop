package com.company.webshop.exceptionhandling;

import com.company.webshop.common.aspects.exception.ExceptionResponse;
import com.company.webshop.common.aspects.exception.ResourceNotFoundException;
import com.company.webshop.common.test.UnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

public class ExceptionHandlingControllerTest extends UnitTest {

    private static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found message";

    @InjectMocks
    private ExceptionHandlingController exceptionHandlingController;

    @Test
    public void resourceNotFound_ReturnsResponseEntityWithResourceNotFoundErrorAndMessage() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE);

        ResponseEntity<ExceptionResponse> result = exceptionHandlingController.resourceNotFound(resourceNotFoundException);

        ArrayList<String> expectedError = newArrayList();
        expectedError.add(RESOURCE_NOT_FOUND_MESSAGE);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getError()).isEqualTo(expectedError);
    }
}