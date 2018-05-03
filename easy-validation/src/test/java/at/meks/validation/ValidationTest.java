package at.meks.validation;

import at.meks.validation.result.ValidationResult;
import at.meks.validation.validations.object.ObjectValidations;
import org.junit.Test;

import static at.meks.validation.validations.string.StringValidations.hasLength;
import static at.meks.validation.validations.string.StringValidations.isNotBlank;
import static org.fest.assertions.api.Assertions.assertThat;

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