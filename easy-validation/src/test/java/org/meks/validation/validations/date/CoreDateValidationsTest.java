package org.meks.validation.validations.date;

import org.junit.Test;
import org.meks.validation.AbstractCoreValidationsTest;
import org.meks.validation.result.ValidationResult;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.fest.assertions.api.Assertions.assertThat;

public class CoreDateValidationsTest extends AbstractCoreValidationsTest {

    private CoreDateValidations validations = new CoreDateValidations();

    @Test
    public void givenSmallerDateWhenIsDateAfterReturnsErrorWithExpectedMessage() {
        ValidationResult result = validations.isDateAfter(now().plus(2, SECONDS), errorDescription)
                .test(LocalDateTime.now());
        assertThat(result.isValid()).as("valid").isFalse();
        assertThat(result.getErrorMessage()).isEqualTo(exptectedMessage);
        assertThat(result.getErrorCode()).isNull();
    }

    @Test
    public void givenEqualDateWhenIsDateAfterReturnsError() {
        LocalDateTime validatedDate = of(2017, 5, 8, 7, 12, 14);
        LocalDateTime comparedDate = of(2017, 5, 8, 7, 12, 14);
        assertThat(validatedDate).isNotSameAs(comparedDate);
        ValidationResult result = validations.isDateAfter(comparedDate, errorDescription).test(validatedDate);
        assertThat(result.isValid()).as("valid").isFalse();
    }

    @Test
    public void givenGreaterDateWhenIsDateAfterReturnsOk() {
        ValidationResult result = validations.isDateAfter(now().minus(1, SECONDS), errorDescription)
                .test(LocalDateTime.now());
        assertThat(result.isValid()).as("valid").isTrue();
    }

}