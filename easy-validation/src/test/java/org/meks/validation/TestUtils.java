package org.meks.validation;

import org.meks.validation.result.ValidationResult;

import static org.fest.assertions.api.Assertions.assertThat;

public class TestUtils {

    private TestUtils() {}

    public static void assertErrorResult(ValidationResult result, String expectedMessage) {
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo(expectedMessage);
    }

    public static void assertValidResult(ValidationResult result) {
        assertThat(result.isValid()).isTrue();
    }
}
