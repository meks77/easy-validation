package at.meks.validation.validations.number;

import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;

public class CoreNumberValidationsTest extends AbstractCoreValidationsTest {

    private CoreNumberValidations validations = new CoreNumberValidations();

    @Test
    public void givenEqualIntWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(10, errorDescription).test(10));
    }

    @Test
    public void givenGreaterIntWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(10, errorDescription).test(11));
    }

    @Test
    public void givenLessIntWhenIsLessReturnOk() {
        assertValidResult(validations.isLessThan(10, errorDescription).test(9));
    }

    @Test
    public void givenEqualDoubleWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(10.1234, errorDescription).test(10.1234));
    }

    @Test
    public void givenGreaterDoubleWhenIsLessReturnError() {
        assertErrorResult(validations.isLessThan(10.1234, errorDescription).test(11.0));
    }

    @Test
    public void givenLessDoubleWhenIsLessReturnOk() {
        assertValidResult(validations.isLessThan(10.1, errorDescription).test(9.99));
    }

    @Test
    public void givenGreaterWhenIsGreaterReturnsOk() {
        assertValidResult(validations.isGreaterThan(10.0, errorDescription).test(10.1));
    }

    @Test
    public void givenLessWhenIsGreaterReturnError() {
        assertErrorResult(validations.isGreaterThan(10.1, errorDescription).test(10.0));
    }

    @Test
    public void givenEqualWhenIsGreaterReturnError() {
        assertErrorResult(validations.isGreaterThan(10.1, errorDescription).test(10.1));
    }

    @Test
    public void given10WhenIsBetween10and12ReturnsOk() {
        assertValidResult(validations.isBetween(10, 12, errorDescription).test(10));
    }

    @Test
    public void given11WhenIsBetween10and12ReturnsOk() {
        assertValidResult(validations.isBetween(10, 12, errorDescription).test(11));
    }

    @Test
    public void given12WhenIsBetween10and12ReturnsOk() {
        assertValidResult(validations.isBetween(10, 12, errorDescription).test(12));
    }

    @Test
    public void given9WhenIsBetween10and12ReturnsError() {
        assertErrorResult(validations.isBetween(10, 12, errorDescription).test(9));
    }

    @Test
    public void given13WhenIsBetween10and12ReturnsError() {
        assertErrorResult(validations.isBetween(10, 12, errorDescription).test(13));
    }

    @Test
    public void givenMinIntWhenIsIntReturnsOk() {
        assertValidResult(validations.isInt(errorDescription).test(Integer.MIN_VALUE));
    }

    @Test
    public void givenMaxIntWhenIsIntReturnsOk() {
        assertValidResult(validations.isInt(errorDescription).test(Integer.MAX_VALUE));
    }

    @Test
    public void givenMoreThanMaxIntWhenIsIntReturnsError() {
        assertErrorResult(validations.isInt(errorDescription).test(((double) Integer.MAX_VALUE) + 1.0));
    }

    @Test
    public void givenLessThanMinIntWhenIsIntReturnsError() {
        assertErrorResult(validations.isInt(errorDescription).test(((double) Integer.MIN_VALUE) - 1.0));
    }

    @Test
    public void givenMinShortWhenIsShotReturnsOk() {
        assertValidResult(validations.isShort(errorDescription).test(Short.MIN_VALUE));
    }

    @Test
    public void givenMaxShortWhenIsShortReturnsOk() {
        assertValidResult(validations.isShort(errorDescription).test(Short.MAX_VALUE));
    }

    @Test
    public void givenMoreThanMaxShortWhenIsShortReturnsError() {
        assertErrorResult(validations.isShort(errorDescription).test(((double) Short.MAX_VALUE) + 1.0));
    }

    @Test
    public void givenLessThanMinShortWhenIsShortReturnsError() {
        assertErrorResult(validations.isShort(errorDescription).test(((double) Short.MIN_VALUE) - 1.0));
    }

    @Test
    public void givenMinByteWhenIsByteReturnsOk() {
        assertValidResult(validations.isByte(errorDescription).test(Byte.MIN_VALUE));
    }

    @Test
    public void givenMaxByteWhenIsByteReturnsOk() {
        assertValidResult(validations.isByte(errorDescription).test(Byte.MAX_VALUE));
    }

    @Test
    public void givenMoreThanMaxByteWhenIsByteReturnsError() {
        assertErrorResult(validations.isByte(errorDescription).test(((double) Byte.MAX_VALUE) + 1.0));
    }

    @Test
    public void givenLessThanMinByteWhenIsByteReturnsError() {
        assertErrorResult(validations.isByte(errorDescription).test(((double) Byte.MIN_VALUE) - 1.0));
    }

}