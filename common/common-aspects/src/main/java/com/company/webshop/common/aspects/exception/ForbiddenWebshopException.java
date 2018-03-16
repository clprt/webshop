package com.company.webshop.common.aspects.exception;

public class ForbiddenWebshopException extends WebShopException {

    public ForbiddenWebshopException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }
}
