package org.meks.validation;

import org.junit.Test;
import org.meks.validation.validations.object.ObjectValidations;
import org.meks.validation.result.ValidationResult;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.meks.validation.validations.string.StringValidations.hasLength;
import static org.meks.validation.validations.string.StringValidations.isNotBlank;

public class ValidationTest {

    @Test
    public void givenJoinedValidationsByAndExecutesRootValidationAsFirst() {
        ValidationResult result = ObjectValidations.<String>notNull().and(isNotBlank()).test(null);
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo("must not be null");
    }

    @Test
    public void givenJoinedValidationsByOrWhenOneIsValidThenResultIsValid() {
        ValidationResult result = ObjectValidations.<String>notNull().or(isNotBlank()).test("  ");
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void givenJoinedInvalidValidationsByOrWhenTestReturnsLastValidationResult() {
        ValidationResult result = ObjectValidations.<String>notNull().or(hasLength(10)).or(isNotBlank()).test(null);
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo("mustn't be blank");
    }

}