package org.meks.validation.validations;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.meks.validation.ErrorMessageResolver;
import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ErrorDescriptionWithMessage;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractValidationsTest<T> {

    final String expectedMessage = "expected message on error";

    @Mock
    private ErrorMessageResolver messageResolver;

    @Mock
    private Validation<T> expectedValidation;

    private  ArgumentCaptor<ErrorDescription> errorDescriptionArgCaptor = getErrorDescCaptor();

    private ArgumentCaptor<ErrorDescription> getErrorDescCaptor() {
        return ArgumentCaptor.forClass(ErrorDescription.class);
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public ErrorMessageResolver getMessageResolver() {
        return messageResolver;
    }

    public Validation<T> getExpectedValidation() {
        return expectedValidation;
    }

    @Before
    public void setStaticFieldMocks() throws IllegalAccessException {
        FieldUtils.writeStaticField(getTestedClass(), "messageResolver", messageResolver, true);
        FieldUtils.writeStaticField(getTestedClass(), "validations", getCoreValidations(), true);
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
        assertThat(errorDescription.getErrorMessage()).isSameAs(expectedMessage);
    }
}
