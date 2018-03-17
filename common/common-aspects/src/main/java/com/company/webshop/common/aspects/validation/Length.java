package com.company.webshop.common.aspects.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.company.webshop.common.aspects.validation.ValidationMessage.FIELD_LENGTH_EXCEEDED;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = LengthValidatorString.class)
@Inherited
public @interface Length {

    String message() default FIELD_LENGTH_EXCEEDED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value() default 255;

}