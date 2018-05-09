package at.meks.validation;

import at.meks.validation.result.ValidationResult;
import at.meks.validation.validations.number.NumberValidations;
import at.meks.validation.validations.object.ObjectValidations;
import at.meks.validation.validations.string.StringValidations;
import org.junit.Test;

import static at.meks.validation.validations.number.NumberValidations.isGreaterThan;
import static at.meks.validation.validations.string.StringValidations.hasLength;
import static at.meks.validation.validations.string.StringValidations.isNotBlank;
import static at.meks.validation.validations.string.StringValidations.isNumeric;
import static org.fest.assertions.api.Assertions.assertThat;

public class ValidationTest {

    @Test
    public void givenJoinedValidationsByAndExecutesRootValidationAsFirst() {
        ValidationResult result = ObjectValidations.<String>notNull().and(isNotBlank()).test(null);
        assertErrorResult(result, "must not be null");
    }

    @Test
    public void givenJoinedValidationsByOrWhenOneIsValidThenResultIsValid() {
        ValidationResult result = ObjectValidations.<String>notNull().or(isNotBlank()).test("  ");
        assertValidResult(result);
    }

    @Test
    public void givenJoinedInvalidValidationsByOrWhenTestReturnsLastValidationResult() {
        ValidationResult result = ObjectValidations.<String>notNull().or(hasLength(10)).or(isNotBlank()).test(null);
        assertErrorResult(result, "mustn't be blank");
    }

    private void assertErrorResult(ValidationResult result, String s) {
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo(s);
    }

    @Test
    public void givenNumericStringWhenIsNumericCanBeCombinedWithNumberValidationsReturnsOk() {
        Validation<String> validation = isNumeric().and(Long::parseLong,isGreaterThan(5));
        assertValidResult(validation.test("11"));
    }

    @Test
    public void givenNumericStringWhenIsNumericCanBeCombinedWithNumberValidationsReturnsNumericError() {
        Validation<String> validation = isNumeric().and(Long::parseLong, isGreaterThan(5));
        assertErrorResult(validation.test("4"), "value must be greater than 5");
    }

    private void assertValidResult(ValidationResult result) {
        assertThat(result.isValid()).isTrue();
    }
}