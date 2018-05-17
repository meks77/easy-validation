package at.meks.validation.validations.number;

import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;

public class CoreNumberValidationsTest extends AbstractCoreValidationsTest {

    private final CoreNumberValidations validations = new CoreNumberValidations();

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