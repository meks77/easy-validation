package at.meks.validation.validations;

import at.meks.validation.result.ErrorDescription;
import at.meks.validation.result.ErrorDescriptionWithCode;

import static org.fest.assertions.api.Assertions.assertThat;

public abstract class AbstractCodeValidationsTest<T> extends AbstractValidationsTest<T> {

    protected static final String EXPECTED_CODE = "my expected code";

    @Override
    public void assertErrorDesc(ErrorDescription errorDescription) {
        assertThat(errorDescription).isInstanceOf(ErrorDescriptionWithCode.class);
        assertThat(errorDescription.getErrorMessage()).isEqualTo(EXPECTED_CODE + " - " + getExpectedMessage());
        assertThat(((ErrorDescriptionWithCode) errorDescription).getErrorCode()).isSameAs(EXPECTED_CODE);
    }
}
