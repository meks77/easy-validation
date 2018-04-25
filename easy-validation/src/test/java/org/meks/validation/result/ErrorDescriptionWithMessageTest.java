package org.meks.validation.result;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ErrorDescriptionWithMessageTest {

    @Test
    public void givenErrorMessageWhenConstructedIsReturnedByGetErrorMessage() {
        String expectedMessage = "expectedMessage";
        assertThat(new ErrorDescriptionWithMessage(expectedMessage).getErrorMessage()).isEqualTo(expectedMessage);
    }
}