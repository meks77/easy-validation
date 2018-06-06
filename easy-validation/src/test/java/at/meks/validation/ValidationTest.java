package at.meks.validation;

import at.meks.validation.result.ValidationResult;
import at.meks.validation.validations.common.CommonValidations;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

import static at.meks.validation.validations.common.CommonValidations.isGreaterThan;
import static at.meks.validation.validations.string.StringValidations.hasLength;
import static at.meks.validation.validations.string.StringValidations.isNotBlank;
import static at.meks.validation.validations.string.StringValidations.isNumeric;
import static org.fest.assertions.api.Assertions.assertThat;

public class ValidationTest {

    @BeforeClass
    public static void setLocalEnglish() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void givenJoinedValidationsByAndExecutesRootValidationAsFirst() {
        ValidationResult result = CommonValidations.<String>notNull().and(isNotBlank()).test(null);
        assertErrorResult(result, "must not be null");
    }

    @Test
    public void givenJoinedValidationsByAndWhenTestThenSecondValidationFails() {
        ValidationResult result = CommonValidations.<String>notNull().and(isNotBlank()).test(" ");
        assertErrorResult(result, "mustn't be blank");
    }

    @Test
    public void givenJoinedValidationsByOrWhenOneIsValidThenResultIsValid() {
        ValidationResult result = CommonValidations.<String>notNull().or(isNotBlank()).test("  ");
        assertValidResult(result);
    }

    @Test
    public void givenJoinedInvalidValidationsByOrWhenTestReturnsLastValidationResult() {
        ValidationResult result = CommonValidations.<String>notNull().or(hasLength(10)).or(isNotBlank()).test(null);
        assertErrorResult(result, "mustn't be blank");
    }

    private void assertErrorResult(ValidationResult result, String s) {
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo(s);
    }

    @Test
    public void givenNumericStringWhenIsNumericCanBeCombinedWithNumberValidationsReturnsOk() {
        Validation<String> validation = isNumeric().and(Long::parseLong, isGreaterThan(5L));
        assertValidResult(validation.test("11"));
    }

    @Test
    public void givenNumericStringWhenIsNumericCanBeCombinedWithNumberValidationsReturnsNumericError() {
        Validation<String> validation = isNumeric().and(Long::parseLong, isGreaterThan(5L));
        assertErrorResult(validation.test("4"), "value must be greater than 5");
    }

    @Test
    public void givenNotNumericStringWhenIsNumericCanBeCombinedWithNumberValidationsReturnsError() {
        Validation<String> validation = isNumeric().and(Long::parseLong,isGreaterThan(5L));
        assertErrorResult(validation.test("a"), "value must be numeric");
    }

    private void assertValidResult(ValidationResult result) {
        assertThat(result.isValid()).isTrue();
    }
}