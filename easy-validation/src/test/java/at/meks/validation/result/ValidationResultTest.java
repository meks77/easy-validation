package at.meks.validation.result;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ValidationResultTest {

    private static final String EXPECTED_MESSAGE = "myMessage";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenErrorDescriptionWhenGetErrorCodeReturnsCodeFromDescription() {
        String expectedCode = "myCode";
        ErrorDescription errorDescription = mockDescription(expectedCode);
        assertThat(ValidationResult.fail(errorDescription).getErrorCode()).isSameAs(expectedCode);
        //noinspection ResultOfMethodCallIgnored
        verify(errorDescription).getErrorCode();
    }

    @Test
    public void givenErrorDescriptionWithMessageOnlyWhenGetErrorCodeReturnNull() {
        ErrorDescription errorDescription = mockDescription(null);
        assertThat(ValidationResult.fail(errorDescription).getErrorCode()).isNull();
        //noinspection ResultOfMethodCallIgnored
        verify(errorDescription).getErrorCode();
    }

    @Test
    public void givenErrorDescriptionWhenGetErrorMessageReturnsMessageFromDescription() {
        ErrorDescription errorDescription = mockDescription(null);
        assertThat(ValidationResult.fail(errorDescription).getErrorMessage()).isEqualTo(EXPECTED_MESSAGE);
        //noinspection ResultOfMethodCallIgnored
        verify(errorDescription).getErrorMessage();
    }

    private ErrorDescription mockDescription(String code) {
        ErrorDescription errorDescription = mock(ErrorDescription.class);
        //noinspection ResultOfMethodCallIgnored
        doReturn(EXPECTED_MESSAGE).when(errorDescription).getErrorMessage();
        //noinspection ResultOfMethodCallIgnored
        doReturn(code).when(errorDescription).getErrorCode();
        return errorDescription;
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
        //noinspection ResultOfMethodCallIgnored
        doReturn(errorMessage).when(errorDescription).getErrorMessage();
        expectedException.expectMessage(String.format("%s: %s", "valueName", errorMessage));
        ValidationResult.fail(errorDescription).throwIfInvalid("valueName");
    }

}