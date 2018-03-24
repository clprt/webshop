package com.company.webshop.common.aspects.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PriceValidator implements ConstraintValidator<Price, BigDecimal> {

    private int maxPower;

    @Override
    public void initialize(Price constraintAnnotation) {
        maxPower = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal != null && bigDecimal.signum() > 0 && bigDecimal.compareTo(BigDecimal.valueOf(10).pow(maxPower)) < 0;
    }
}
