package com.company.webshop.common.aspects.util;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    public static List<String> getDefaultMessages(BindingResult bindingResult) {
        List<String> defaultMessages = new ArrayList<String>();
        bindingResult.getAllErrors()
                .forEach(objectError -> defaultMessages.add(objectError.getDefaultMessage()));
        return defaultMessages;
    }
}