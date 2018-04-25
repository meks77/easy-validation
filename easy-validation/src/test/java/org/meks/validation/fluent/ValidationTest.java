package org.meks.validation.fluent;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.meks.validation.fluent.result.ValidationResult;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.meks.validation.fluent.ObjectValidations.notNull;
import static org.meks.validation.fluent.StringValidations.hasLength;
import static org.meks.validation.fluent.StringValidations.isNotBlank;

public class ValidationTest {

    @Test
    public void givenJoinedValidationsByAndExecutesRootValidationAsFirst() {
        ValidationResult result = notNull().and(isNotBlank()).test(() -> null);
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo("must not be null");
    }

    @Test
    public void givenJoinedValidationsByOrWhenOneIsValidThenResultIsValid() {
        ValidationResult result = notNull().or(isNotBlank()).test(() -> "  ");
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void givenJoinedInvalidValidationsByOrWhenTestReturnsLastValidationResult() {
        ValidationResult result = notNull().or(hasLength(10)).or(isNotBlank()).test(() -> null);
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo("mustn't be blank");
    }

}