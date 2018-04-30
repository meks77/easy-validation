package org.meks.validation.validations;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ErrorDescriptionWithCode;
import org.meks.validation.result.ValidationResult;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractCoreValidationsTest {

    private final String exptectedMessage = "my expected error message";
    private final String expectedCode = "myExpectedCode";

    @Mock
    protected ErrorDescriptionWithCode errorDescription;

    @Before
    public void mockErrorDescription() {
        doReturn(exptectedMessage).when(errorDescription).getErrorMessage();
        //noinspection ResultOfMethodCallIgnored
        doReturn(expectedCode).when(errorDescription).getErrorCode();
    }


    protected void assertErrorResult(ValidationResult result) {
        assertThat(result.isValid()).as("valid").isFalse();
        assertThat(result.getErrorMessage()).isEqualTo(exptectedMessage);
        assertThat(result.getErrorCode()).isEqualTo(expectedCode);
    }

    protected void assertValidResult(ValidationResult result) {
        assertThat(result.isValid()).as("valid").isTrue();
    }
}
