package at.meks.validation.validations;

import at.meks.validation.result.ErrorDescription;
import at.meks.validation.result.ValidationResult;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractCoreValidationsTest {

    private final String exptectedMessage = "my expected error message";
    private final String expectedCode = "myExpectedCode";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected ErrorDescription errorDescription;

    private Supplier<ErrorDescription> errorDescriptionSupplier = () -> errorDescription;

    protected Supplier<ErrorDescription> getErrorDescriptionSupplier() {
        return errorDescriptionSupplier;
    }

    @Before
    public void mockErrorDescription() {
        //noinspection ResultOfMethodCallIgnored
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
