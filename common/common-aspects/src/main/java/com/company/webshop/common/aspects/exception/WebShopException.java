package com.company.webshop.common.aspects.exception;

public class WebShopException extends RuntimeException {

    public WebShopException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getValue());
    }
}
