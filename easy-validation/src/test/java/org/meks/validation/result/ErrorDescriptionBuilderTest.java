package org.meks.validation.result;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ErrorDescriptionBuilderTest {

    @Test
    public void givenMessageAndCodeWhenWithCodeReturnsDescriptionWithCodeAndMessage() {
        String expectedCode = "expectedCode";
        String expectedMessage = "expectedMessage";
        ErrorDescription description = ErrorDescriptionBuilder.withCode(expectedMessage, expectedCode);
        assertThat(description.getErrorMessage()).isEqualTo(expectedCode + " - " + expectedMessage);
        assertThat(description).isInstanceOf(ErrorDescriptionWithCode.class);
        assertThat(((ErrorDescriptionWithCode) description).getErrorCode()).isEqualTo(expectedCode);
    }

    @Test
    public void givenMessageWhenWithMessageReturnsDescriptionWithMessage() {
        String expectedMessage = "myMessage";
        ErrorDescription description = ErrorDescriptionBuilder.withMessage(expectedMessage);
        assertThat(description).isInstanceOf(ErrorDescriptionWithMessage.class);
        assertThat(description.getErrorMessage()).isEqualTo(expectedMessage);
    }
}