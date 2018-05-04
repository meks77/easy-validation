package at.meks.validation.result;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ValidationExceptionTest {

    private static final String EXPECTED_ERROR_MESSAGE = "errorMessage";
    private static final String EXPECTED_ERROR_CODE = "errorCode";

    @Test
    public void givenErrorDescriptionWithCodeWhenMessageReturnsMessageWithCode() {
        String expectedValueDesc = "valueDescription";
        ValidationException exception = new ValidationException(expectedValueDesc, getErrorDescWithCode());
        assertThat(exception.getMessage()).isEqualTo(
                String.format("%s: %s - %s", expectedValueDesc, EXPECTED_ERROR_CODE, EXPECTED_ERROR_MESSAGE));
    }

    @Test
    public void givenErrorDescriptionWithoutCodeWhenMessageReturnsMessageWithoutCode() {
        String expectedValueDesc = "valueDescription";
        ValidationException exception = new ValidationException(expectedValueDesc, getErrorDescWithMessageOnly());
        assertThat(exception.getMessage()).isEqualTo(
                String.format("%s: %s", expectedValueDesc, EXPECTED_ERROR_MESSAGE));
    }

    @Test
    public void givenErrorDescriptionWithCodeWhenGetErrorCodeReturnExpectedCode() {
        ErrorDescription errorDescription = getErrorDescWithCode();
        ValidationException exception = new ValidationException("whatever", errorDescription);
        assertThat(exception.getErrorCode()).isEqualTo(errorDescription.getErrorCode());
    }

    @Test
    public void givenErrorDescriptionWithoutCodeWhenGetErrorCodeReturnNull() {
        ErrorDescription errorDescription = getErrorDescWithMessageOnly();
        ValidationException exception = new ValidationException("whatever", errorDescription);
        assertThat(exception.getErrorCode()).isNull();
    }

    private ErrorDescription getErrorDescWithMessageOnly() {
        return ErrorDescriptionBuilder.withMessage(EXPECTED_ERROR_MESSAGE);
    }

    private ErrorDescription getErrorDescWithCode() {
        return ErrorDescriptionBuilder.withCode(EXPECTED_ERROR_MESSAGE, EXPECTED_ERROR_CODE);
    }


}