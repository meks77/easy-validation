package org.meks.validation.validations;

import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ErrorDescriptionWithCode;

import static org.fest.assertions.api.Assertions.assertThat;

public abstract class AbstractCodeValidationsTest<T> extends AbstractValidationsTest<T> {

    protected final String expectedCode = "my expected code";

    @Override
    public void assertErrorDesc(ErrorDescription errorDescription) {
        assertThat(errorDescription).isInstanceOf(ErrorDescriptionWithCode.class);
        assertThat(errorDescription.getErrorMessage()).isEqualTo(expectedCode + " - " + expectedMessage);
        assertThat(((ErrorDescriptionWithCode) errorDescription).getErrorCode()).isSameAs(expectedCode);
    }
}
