package com.company.webshop.common.aspects.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.company.webshop.common.aspects.validation.ValidationMessage.INVALID_PRICE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = PriceValidator.class)
@Inherited
public @interface Price {

    String message() default INVALID_PRICE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value() default 17;

}