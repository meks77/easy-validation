package org.meks.validation;

import org.junit.Test;
import org.meks.validation.result.ValidationResult;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.meks.validation.DateValidations.isDateAfter;

public class DateValidationsTest {

    @Test
    public void givenSmallerDateWhenIsDateAfterReturnsError() {
        ValidationResult result = isDateAfter(now().plus(2, SECONDS)).test(LocalDateTime::now);
        assertThat(result.isValid()).as("valid").isFalse();
    }

    @Test
    public void givenEqualDateWhenIsDateAfterReturnsError() {
        LocalDateTime validatedDate = of(2017, 5, 8, 7, 12, 14);
        LocalDateTime comparedDate = of(2017, 5, 8, 7, 12, 14);
        assertThat(validatedDate).isNotSameAs(comparedDate);
        ValidationResult result = isDateAfter(comparedDate).test(() -> validatedDate);
        assertThat(result.isValid()).as("valid").isFalse();
    }

    @Test
    public void givenGreaterDateWhenIsDateAfterReturnsOk() {
        ValidationResult result = isDateAfter(now().minus(1, SECONDS)).test(LocalDateTime::now);
        assertThat(result.isValid()).as("valid").isTrue();
    }

    @Test
    public void givenErrorCodeWhenIsDateAfterReturnsErrorCode() {
        String expectedErrorCode = "myErrorCode";
        ValidationResult result = isDateAfter(now().plus(2, SECONDS), expectedErrorCode).test(LocalDateTime::now);
        assertThat(result.getErrorCode()).isEqualTo(expectedErrorCode);
    }
}