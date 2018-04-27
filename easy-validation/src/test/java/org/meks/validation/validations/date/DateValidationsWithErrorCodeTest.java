package org.meks.validation.validations.date;

import org.junit.Test;
import org.meks.validation.result.ValidationResult;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.meks.validation.validations.date.DateValidationsWithErrorCode.isDateAfter;

public class DateValidationsWithErrorCodeTest {

    @Test
    public void givenErrorCodeWhenIsDateAfterReturnsErrorCode() {
        String expectedErrorCode = "myErrorCode";
        ValidationResult result = isDateAfter(now().plus(2, SECONDS), expectedErrorCode).test(LocalDateTime.now());
        assertThat(result.getErrorCode()).isEqualTo(expectedErrorCode);
    }

}