package at.meks.validation.validations;

import at.meks.validation.result.ErrorDescription;

import static org.fest.assertions.api.Assertions.assertThat;

public abstract class AbstractCodeValidationsTest<T> extends AbstractValidationsTest<T> {

    protected static final String EXPECTED_CODE = "my expected code";

    @Override
    public void assertErrorDesc(ErrorDescription errorDescription) {
        assertThat(errorDescription).isInstanceOf(ErrorDescription.class);
        assertThat(errorDescription.getErrorMessage()).isEqualTo(getExpectedMessage());
        assertThat(errorDescription.getErrorCode()).isSameAs(EXPECTED_CODE);
    }
}
