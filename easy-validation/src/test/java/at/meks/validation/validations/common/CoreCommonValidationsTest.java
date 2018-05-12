package at.meks.validation.validations.common;

import at.meks.validation.result.ValidationResult;
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
        ValidationResult result = validations.notNull(errorDescription).test(null);
        assertErrorResult(result);
    }

}