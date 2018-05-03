package at.meks.validation.validations.object;

import at.meks.validation.result.ValidationResult;
import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;


public class CoreObjectValidationsTest extends AbstractCoreValidationsTest {

    private CoreObjectValidations validations = new CoreObjectValidations();

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