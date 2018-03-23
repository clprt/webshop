package com.company.webshop.common.aspects.exception;

public enum ExceptionMessage {
    
    RESOURCE_NOT_FOUND("Resource not found"),
    FORBIDDEN("Forbidden"),
    EMAIL_ADDRESS_ALREADY_IN_USE("Email address already in use"),
    ITEM_NAME_ALREADY_IN_USE("Item name already in use");

    private final String value;

    ExceptionMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
