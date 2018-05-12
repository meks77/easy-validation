package at.meks.validation.validations;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
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
    private Validation<T> expectedValidation;

    private  ArgumentCaptor<ErrorDescription> errorDescriptionArgCaptor = getErrorDescCaptor();
    private  ArgumentCaptor<Supplier<ErrorDescription>> errorDescSupplierArgCaptor = getErrorDescSupplierCaptor();

    private ArgumentCaptor<ErrorDescription> getErrorDescCaptor() {
        return ArgumentCaptor.forClass(ErrorDescription.class);
    }

    private ArgumentCaptor<Supplier<ErrorDescription>> getErrorDescSupplierCaptor() {
        //noinspection unchecked
        return ArgumentCaptor.forClass(Supplier.class);
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

    public void doAssertionsAndVerifications(Validation<T> validation, VerifierWithErrorDescCaptor coreMethodVerifier) {
        assertForExpectedValidation(validation);
        coreMethodVerifier.doVerification(errorDescriptionArgCaptor);
        assertErrorDesc(errorDescriptionArgCaptor.getValue());
    }

    public void doAssertionsAndVerificationsWithSupplier(Validation<T> validation, VerifierWithErrorDescSupplierCaptor coreMethodVerifier) {
        assertForExpectedValidation(validation);
        coreMethodVerifier.doVerification(errorDescSupplierArgCaptor);
        assertErrorDesc(errorDescSupplierArgCaptor.getValue().get());
    }

    public void assertErrorDesc(ErrorDescription errorDescription) {
        assertThat(errorDescription.getErrorMessage()).isSameAs(EXPECTED_MESSAGE);
    }
}
