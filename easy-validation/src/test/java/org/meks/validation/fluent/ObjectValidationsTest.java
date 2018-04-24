package org.meks.validation.fluent;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;


public class ObjectValidationsTest {

    @Test
    public void givenNotNullWhenNotNullReturnsValidResult() {
        assertThat(ObjectValidations.notNull().test(() -> "a").isValid()).isTrue();
    }

    @Test
    public void givenNullWhenNotNullReturnsErrorResult() {
        assertThat(ObjectValidations.notNull().test(() -> null).isValid()).isFalse();
    }

}