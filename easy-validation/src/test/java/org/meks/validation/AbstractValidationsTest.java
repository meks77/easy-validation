package org.meks.validation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ErrorDescriptionWithMessage;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public abstract class AbstractValidationsTest {

    protected final String expectedMessage = "expected message on error";

    @Mock
    protected ErrorMessageResolver messageResolver;

    protected void assertErrorDescMessageOnly(ArgumentCaptor<ErrorDescription>
            errorDescriptionArgCaptor) {
        ErrorDescription errorDescription = errorDescriptionArgCaptor.getValue();
        assertThat(errorDescription).isInstanceOf(ErrorDescriptionWithMessage.class);
        assertThat(errorDescription.getErrorMessage()).isSameAs(expectedMessage);
    }

    protected ArgumentCaptor<ErrorDescription> getErrorDescCaptor() {
        return ArgumentCaptor.forClass(ErrorDescription.class);
    }

    @Before
    public void setStaticFieldMocks() throws IllegalAccessException {
        FieldUtils.writeStaticField(getTestedClass(), "messageResolver", messageResolver, true);
        FieldUtils.writeStaticField(getTestedClass(), "validations", getCoreValidations(), true);
    }

    protected abstract Class<?> getTestedClass();

    protected abstract Object getCoreValidations();

    @SuppressWarnings("unchecked")
    protected  <T> Validation<T> mockValidation() {
        return mock(Validation.class);
    }
}
