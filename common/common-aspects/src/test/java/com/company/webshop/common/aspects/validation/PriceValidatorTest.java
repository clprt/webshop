package com.company.webshop.common.aspects.validation;

import com.company.webshop.common.test.UnitTest;
import org.fest.reflect.core.Reflection;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceValidatorTest extends UnitTest {

    private PriceValidator priceValidator;

    @Before
    public void setUp() {
        priceValidator = new PriceValidator();
        Reflection.field("maxPower").ofType(int.class).in(priceValidator).set(7);
    }

    @Test
    public void isValid_NullReturnsFalse() {
        boolean result = priceValidator.isValid(null, null);

        assertThat(result).isFalse();
    }

    @Test
    public void isValid_ZeroReturnsFalse() {
        boolean result = priceValidator.isValid(BigDecimal.ZERO, null);

        assertThat(result).isFalse();
    }

    @Test
    public void isValid_NegativeReturnsFalse() {
        boolean result = priceValidator.isValid(BigDecimal.valueOf(-0.01), null);

        assertThat(result).isFalse();
    }

    @Test
    public void isValid_BiggerThanMaxPowerReturnsFalse() {
        boolean result = priceValidator.isValid(BigDecimal.valueOf(10000000), null);

        assertThat(result).isFalse();
    }

    @Test
    public void isValid_SmallerThanMaxPowerReturnsFalse() {
        boolean result = priceValidator.isValid(BigDecimal.valueOf(9999999.99), null);

        assertThat(result).isTrue();
    }
}