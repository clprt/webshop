package com.company.webshop.common.aspects.validation;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fest.reflect.core.Reflection.field;

public class LengthValidatorStringTest {

    private LengthValidatorString lengthValidator;

    @Before
    public void setUp() {
        lengthValidator = new LengthValidatorString();
        field("maxLength").ofType(int.class).in(lengthValidator).set(3);
    }

    @Test
    public void valid() {
        assertThat(lengthValidator.isValid(null, null)).isTrue();
        assertThat(lengthValidator.isValid("", null)).isTrue();
        assertThat(lengthValidator.isValid("a", null)).isTrue();
        assertThat(lengthValidator.isValid("ab", null)).isTrue();
        assertThat(lengthValidator.isValid("abc", null)).isTrue();
    }

    @Test
    public void invalid() {
        assertThat(lengthValidator.isValid("abcd", null)).isFalse();
        assertThat(lengthValidator.isValid("      ", null)).isFalse();
    }

}