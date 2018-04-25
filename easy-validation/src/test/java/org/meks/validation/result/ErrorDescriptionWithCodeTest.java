package org.meks.validation.result;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ErrorDescriptionWithCodeTest {

    @Test
    public void givenErrorMessageAndCodeWhenConstructedIsReturnedByGetErrorMessage() {
        ErrorDescriptionWithCode description = new ErrorDescriptionWithCode("expectedMessage", "code");
        assertThat(description.getErrorMessage()).isEqualTo("code - expectedMessage");
    }

    @Test
    public void givenErrorCodeWhenConstructedIsReturnedByGetErrorCode() {
        String expectedCode = "expectedCode";
        assertThat(new ErrorDescriptionWithCode("message", expectedCode).getErrorCode()).isEqualTo(expectedCode);
    }
}