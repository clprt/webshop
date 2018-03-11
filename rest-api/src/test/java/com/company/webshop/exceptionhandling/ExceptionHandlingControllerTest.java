package com.company.webshop.exceptionhandling;

import com.company.webshop.common.aspects.exception.ExceptionResponse;
import com.company.webshop.common.aspects.exception.ResourceNotFoundException;
import com.company.webshop.common.aspects.util.ValidationUtil;
import com.company.webshop.common.test.UnitTest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;
import static org.mockito.Mockito.when;

public class ExceptionHandlingControllerTest extends UnitTest {

    private static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found message";
    private static final String MESSAGE_1 = "Message 1";
    private static final String MESSAGE_2 = "Message 2";

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private BindingResult bindingResult;
    @InjectMocks
    private ExceptionHandlingController exceptionHandlingController;

    @Test
    public void resourceNotFound_ReturnsResponseEntityWithNotFoundErrorAndResourceNotFoundErrorMessage() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE);

        ResponseEntity<ExceptionResponse> result = exceptionHandlingController.resourceNotFound(resourceNotFoundException);

        ArrayList<String> expectedError = newArrayList();
        expectedError.add(RESOURCE_NOT_FOUND_MESSAGE);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getError()).isEqualTo(expectedError);
    }

    @Test
    public void invalidInput_ReturnsResponseEntityWithBadRequestErrorAndValidationErrorMessages() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        ArrayList<ObjectError> objectErrors = Lists.newArrayList();
        objectErrors.add(new ObjectError("ObjectName", MESSAGE_1));
        objectErrors.add(new ObjectError("ObjectName", MESSAGE_2));
        when(bindingResult.getAllErrors()).thenReturn(objectErrors);

        ResponseEntity<ExceptionResponse> result = exceptionHandlingController.invalidInput(methodArgumentNotValidException);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().getError()).containsExactlyInAnyOrder(MESSAGE_1, MESSAGE_2);
    }
}