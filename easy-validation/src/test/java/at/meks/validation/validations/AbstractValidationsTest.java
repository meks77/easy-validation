package at.meks.validation.validations;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.result.ErrorDescriptionWithMessage;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
public abstract class AbstractValidationsTest<T> {

    private static final String EXPECTED_MESSAGE = "expected message on error";

    @Mock
    private ErrorMessageResolver messageResolver;

    @Mock
    private Validation<T> expectedValidation;

    private  ArgumentCaptor<ErrorDescription> errorDescriptionArgCaptor = getErrorDescCaptor();

    private ArgumentCaptor<ErrorDescription> getErrorDescCaptor() {
        return ArgumentCaptor.forClass(ErrorDescription.class);
    }

    public String getExpectedMessage() {
        return EXPECTED_MESSAGE;
    }

    public ErrorMessageResolver getMessageResolver() {
        return messageResolver;
    }

    public Validation<T> getExpectedValidation() {
        return expectedValidation;
    }

    @Before
    public void setStaticFieldMocks() {
        Whitebox.setInternalState(getTestedClass(), ErrorMessageResolver.class, messageResolver);
        Whitebox.setInternalState(getTestedClass(), "validations", getCoreValidations());
    }

    protected abstract Class<?> getTestedClass();

    protected abstract Object getCoreValidations();

    public void assertForExpectedValidation(Validation<T> validation) {
        assertThat(validation).isSameAs(expectedValidation);
    }

    public void doAssertionsAndVerifications(Validation<T> validation, Verifier coreMethodVerifier) {
        assertForExpectedValidation(validation);
        coreMethodVerifier.doVerification(errorDescriptionArgCaptor);
        assertErrorDesc(errorDescriptionArgCaptor);
    }

    private void assertErrorDesc(ArgumentCaptor<ErrorDescription>
                                         errorDescriptionArgCaptor) {
        ErrorDescription errorDescription = errorDescriptionArgCaptor.getValue();
        assertErrorDesc(errorDescription);
    }

    public void assertErrorDesc(ErrorDescription errorDescription) {
        assertThat(errorDescription).isInstanceOf(ErrorDescriptionWithMessage.class);
        assertThat(errorDescription.getErrorMessage()).isSameAs(EXPECTED_MESSAGE);
    }
}
