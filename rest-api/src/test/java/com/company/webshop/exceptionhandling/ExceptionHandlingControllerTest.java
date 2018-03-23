package com.company.webshop.exceptionhandling;

import com.company.webshop.common.aspects.exception.ExceptionMessage;
import com.company.webshop.common.aspects.exception.NotUniqueWebShopException;
import com.company.webshop.common.aspects.exception.ExceptionResponse;
import com.company.webshop.common.aspects.exception.ForbiddenWebshopException;
import com.company.webshop.common.aspects.exception.ResourceNotFoundWebshopException;
import com.company.webshop.common.test.UnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.company.webshop.common.aspects.exception.ExceptionMessage.FORBIDDEN;
import static com.company.webshop.common.aspects.exception.ExceptionMessage.RESOURCE_NOT_FOUND;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ExceptionHandlingControllerTest extends UnitTest {

    private static final String MESSAGE_1 = "Message 1";
    private static final String MESSAGE_2 = "Message 2";
    public static final ExceptionMessage PROPERTY_ALREADY_IN_USE = ExceptionMessage.EMAIL_ADDRESS_ALREADY_IN_USE;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private BindingResult bindingResult;
    @InjectMocks
    private ExceptionHandlingController exceptionHandlingController;

    @Test
    public void resourceNotFound_ReturnsResponseEntityWithNotFoundErrorAndResourceNotFoundErrorMessage() {
        ResourceNotFoundWebshopException resourceNotFoundWebshopException = new ResourceNotFoundWebshopException(RESOURCE_NOT_FOUND);

        ResponseEntity<ExceptionResponse> result = exceptionHandlingController.resourceNotFound(resourceNotFoundWebshopException);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getError()).isEqualTo(newArrayList(RESOURCE_NOT_FOUND.getValue()));
    }

    @Test
    public void invalidInput_ReturnsResponseEntityWithBadRequestErrorAndValidationErrorMessages() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(newArrayList(new ObjectError("ObjectName", MESSAGE_1), new ObjectError("ObjectName", MESSAGE_2)));

        ResponseEntity<ExceptionResponse> result = exceptionHandlingController.invalidInput(methodArgumentNotValidException);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody().getError()).containsExactlyInAnyOrder(MESSAGE_1, MESSAGE_2);
    }

    @Test
    public void accessControlViolation_ReturnsResponseEntityWithForbiddenErrorAndForbiddenErrorMessage() {
        ForbiddenWebshopException forbiddenWebshopException = new ForbiddenWebshopException(FORBIDDEN);

        ResponseEntity<?> result = exceptionHandlingController.accessControlViolation(forbiddenWebshopException);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void propertyNotUnique_ReturnsResponseEntityWithConflictErrorAndPropertyAlreadyInUseErrorMessage() {
        NotUniqueWebShopException notUniqueWebShopException = new NotUniqueWebShopException(PROPERTY_ALREADY_IN_USE);

        ResponseEntity<ExceptionResponse> result = exceptionHandlingController.propertyNotUnique(notUniqueWebShopException);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getBody().getError()).isEqualTo(newArrayList(PROPERTY_ALREADY_IN_USE.getValue()));
    }

}