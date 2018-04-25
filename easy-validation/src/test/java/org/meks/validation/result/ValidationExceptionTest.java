package org.meks.validation.result;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ValidationExceptionTest {

    @Test
    public void givenValueDescriptionAndErrorDescriptionWhenConstructedThenGetMessageReturnsExpected() {
        String expectedErrorMessage = "errorMessage";
        String expectedErrorCode = "errorCode";
        ErrorDescriptionWithCode expectedErrorDesc = new ErrorDescriptionWithCode(expectedErrorMessage, expectedErrorCode);
        String expectedValueDesc = "valueDescription";
        ValidationException exception = new ValidationException(expectedValueDesc, expectedErrorDesc);
        assertThat(exception.getMessage()).isEqualTo(
                String.format("%s: %s - %s", expectedValueDesc, expectedErrorCode, expectedErrorMessage));
    }
}