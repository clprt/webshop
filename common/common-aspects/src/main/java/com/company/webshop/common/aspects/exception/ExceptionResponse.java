package com.company.webshop.common.aspects.exception;

import java.util.List;

public class ExceptionResponse {

    List<String> error;

    public ExceptionResponse() {
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}