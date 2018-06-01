package at.meks.validation.validations;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
public abstract class AbstractValidationsTest<T> {

    private static final String EXPECTED_MESSAGE = "expected message on error";

    @Mock
    private ErrorMessageResolver messageResolver;

    @Mock
    private Validation expectedValidation;

    @Captor
    private  ArgumentCaptor<ErrorDescription> errorDescriptionArgCaptor;

    @Captor
    private  ArgumentCaptor<Supplier<ErrorDescription>> errorDescSupplierArgCaptor;

    public String getExpectedMessage() {
        return EXPECTED_MESSAGE;
    }

    public ErrorMessageResolver getMessageResolver() {
        return messageResolver;
    }

    public <V> Validation<V> getExpectedValidation() {
        //noinspection unchecked
        return expectedValidation;
    }

    @Before
    public void setStaticFieldMocks() {
        Whitebox.setInternalState(getTestedClass(), ErrorMessageResolver.class, messageResolver);
        Whitebox.setInternalState(getTestedClass(), "VALIDATIONS", getCoreValidations());
    }

    protected abstract Class<?> getTestedClass();

    protected abstract Object getCoreValidations();

    public void assertForExpectedValidation(Validation validation) {
        assertThat(validation).isSameAs(expectedValidation);
    }

    public void doAssertionsAndVerifications(Validation<T> validation, VerifierWithErrorDescCaptor coreMethodVerifier) {
        assertForExpectedValidation(validation);
        coreMethodVerifier.doVerification(errorDescriptionArgCaptor);
        assertErrorDesc(errorDescriptionArgCaptor.getValue());
    }

    public <V> void doAssertionsAndVerificationsWithSupplier(Validation<V> validation, VerifierWithErrorDescSupplierCaptor coreMethodVerifier) {
        assertForExpectedValidation(validation);
        coreMethodVerifier.doVerification(errorDescSupplierArgCaptor);
        assertErrorDesc(errorDescSupplierArgCaptor.getValue().get());
    }

    public void assertErrorDesc(ErrorDescription errorDescription) {
        assertThat(errorDescription.getErrorMessage()).isSameAs(EXPECTED_MESSAGE);
    }
}
