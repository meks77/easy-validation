package at.meks.validation.result;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ErrorDescriptionTest {

    @Test
    public void givenErrorMessageAndCodeWhenConstructedIsReturnedByGetErrorMessage() {
        String expectedMessage = "expectedMessage";
        ErrorDescription description = ErrorDescriptionBuilder.withMessage(expectedMessage);
        assertThat(description.getErrorMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void givenErrorCodeWhenConstructedIsReturnedByGetErrorCode() {
        String expectedCode = "expectedCode";
        assertThat(ErrorDescriptionBuilder.withCode("message", expectedCode).getErrorCode()).isEqualTo(expectedCode);
    }
}