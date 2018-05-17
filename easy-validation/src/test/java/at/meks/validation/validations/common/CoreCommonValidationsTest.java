package at.meks.validation.validations.common;

import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;


public class CoreCommonValidationsTest extends AbstractCoreValidationsTest {

    private final CoreCommonValidations validations = new CoreCommonValidations();

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

    @Test
    public void givenEqualIntWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(() -> 10, () -> errorDescription).test(10));
    }

    @Test
    public void givenGreaterIntWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(() -> 10, () -> errorDescription).test(11));
    }

    @Test
    public void givenLessIntWhenIsLessReturnOk() {
        assertValidResult(validations.isLessThan(() -> 10, () -> errorDescription).test(9));
    }

    @Test
    public void givenEqualDoubleWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(() -> 10.1234, () -> errorDescription).test(10.1234));
    }

    @Test
    public void givenGreaterDoubleWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(() -> 10.1234, () -> errorDescription).test(11.0));
    }

    @Test
    public void givenLessDoubleWhenIsLessReturnOk() {
        assertValidResult(validations.isLessThan(() -> 10.1, () -> errorDescription).test(9.99));
    }

    @Test
    public void givenGreaterWhenIsGreaterReturnsOk() {
        assertValidResult(validations.isGreaterThan(() -> 10.0, () -> errorDescription).test(10.1));
    }

    @Test
    public void givenLessWhenIsGreaterReturnError() {
        assertErrorResult(validations.isGreaterThan(() -> 10.1, () -> errorDescription).test(10.0));
    }

    @Test
    public void givenEqualWhenIsGreaterReturnError() {
        assertErrorResult(validations.isGreaterThan(() -> 10.1, () -> errorDescription).test(10.1));
    }

    @Test
    public void given10WhenIsBetween10and12ReturnsOk() {
        assertValidResult(validations.isBetween(() -> 10, () -> 12, () -> errorDescription).test(10));
    }

    @Test
    public void given11WhenIsBetween10and12ReturnsOk() {
        assertValidResult(validations.isBetween(() -> 10, () -> 12, () -> errorDescription).test(11));
    }

    @Test
    public void given12WhenIsBetween10and12ReturnsOk() {
        assertValidResult(validations.isBetween(() -> 10, () -> 12, () -> errorDescription).test(12));
    }

    @Test
    public void given9WhenIsBetween10and12ReturnsError() {
        assertErrorResult(validations.isBetween(() -> 10, () -> 12, () -> errorDescription).test(9));
    }

    @Test
    public void given13WhenIsBetween10and12ReturnsError() {
        assertErrorResult(validations.isBetween(() -> 10, () -> 12, () -> errorDescription).test(13));
    }


}