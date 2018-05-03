package at.meks.validation.result;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ValidationResultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenErrorDescriptionWithErrorCodeWhenGetErrorCodeReturnCodeOfDesc() {
        ErrorDescriptionWithCode errorDescription = mock(ErrorDescriptionWithCode.class);
        String expectedErrorCode = "myCode";
        //noinspection ResultOfMethodCallIgnored
        doReturn(expectedErrorCode).when(errorDescription).getErrorCode();
        assertThat(ValidationResult.fail(errorDescription).getErrorCode()).isEqualTo(expectedErrorCode);
        //noinspection ResultOfMethodCallIgnored
        verify(errorDescription).getErrorCode();
    }

    @Test
    public void givenErrorDescriptionWithMessageOnlyWhenGetErrorCodeReturnNull() {
        ErrorDescriptionWithMessage errorDescription = mock(ErrorDescriptionWithMessage.class);
        assertThat(ValidationResult.fail(errorDescription).getErrorCode()).isNull();
    }

    @Test
    public void givenErrorDescriptionWhenGetErrorMessageReturnsMessageFromDescription() {
        ErrorDescription errorDescription = mock(ErrorDescription.class);
        String expectedMessage = "myMessage";
        doReturn(expectedMessage).when(errorDescription).getErrorMessage();
        assertThat(ValidationResult.fail(errorDescription).getErrorMessage()).isEqualTo(expectedMessage);
        verify(errorDescription).getErrorMessage();
    }

    @Test
    public void givenOkValidationWhenIsValidReturnsTrue() {
        assertThat(ValidationResult.ok().isValid()).isTrue();
    }

    @Test
    public void givenErrorValidationWhenIsValidReturnsFalse() {
        assertThat(ValidationResult.fail(mock(ErrorDescription.class)).isValid()).isFalse();
    }

    @Test
    public void whenOkThenValidResultIsReturned() {
        assertThat(ValidationResult.ok().isValid()).isTrue();
    }

    @Test
    public void givenErrorDescriptionWhenFailReturnsInvalidResult() {
        assertThat(ValidationResult.fail(mock(ErrorDescription.class)).isValid()).isFalse();
    }

    @Test
    public void givenOkResulWhenThrowIfInvalidThenNotExceptionIsThrown() throws ValidationException {
        ValidationResult.ok().throwIfInvalid("valueName");
    }

    @Test
    public void givenErrorResulWhenThrowIfInvalidThenNotExceptionIsThrown() throws ValidationException {
        expectedException.expect(ValidationException.class);
        String errorMessage = "my expected message";
        ErrorDescription errorDescription = mock(ErrorDescription.class);
        doReturn(errorMessage).when(errorDescription).getErrorMessage();
        expectedException.expectMessage(String.format("%s: %s", "valueName", errorMessage));
        ValidationResult.fail(errorDescription).throwIfInvalid("valueName");
    }

}