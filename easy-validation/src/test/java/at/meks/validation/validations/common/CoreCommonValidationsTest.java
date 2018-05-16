package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;


public class CoreCommonValidationsTest extends AbstractCoreValidationsTest {

    private CoreCommonValidations validations = new CoreCommonValidations();

    @Test
    public void givenNotNullWhenNotNullReturnsValidResult() {
        assertValidResult(validations.notNull(errorDescription).test("a"));
    }

    @Test
    public void givenNullWhenNotNullReturnsErrorResult() {
        assertErrorResult(validations.notNull(errorDescription).test(null));
    }

    @Test
    public void givenEqualObjectWhenIsEqualToReturnsOk() {
        assertValidResult(validations.isEqualTo(() -> 5L, () -> errorDescription).test(5L));
    }

    @Test
    public void givenNotEqualObjectWhenIsEqualToReturnsError() {
        assertErrorResult(validations.isEqualTo(() -> 5L, () -> errorDescription).test(4L));
    }

    @Test
    public void givenEqualObjectWhenNotEqualReturnsError() {
        assertErrorResult(validations.isNotEqualTo(() -> 6L, () -> errorDescription).test(6L));
    }

    @Test
    public void givenNotEqualObjectWhenIsNotEqualReturnsOk() {
        assertValidResult(validations.isNotEqualTo(() -> 5L, () -> errorDescription).test(6L));
    }

}