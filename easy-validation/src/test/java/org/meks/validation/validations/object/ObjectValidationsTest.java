package org.meks.validation.validations.object;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;


public class ObjectValidationsTest {

    @Test
    public void givenNotNullWhenNotNullReturnsValidResult() {
        Assertions.assertThat(ObjectValidations.notNull().test("a").isValid()).isTrue();
    }

    @Test
    public void givenNullWhenNotNullReturnsErrorResult() {
        Assertions.assertThat(ObjectValidations.notNull().test(null).isValid()).isFalse();
    }

}