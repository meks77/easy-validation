package at.meks.validation.simple;

import at.meks.validation.result.ValidationException;
import org.fest.assertions.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;

public class SimpleValidationExamplesTest {

    private SimpleValidationExamples examples = new SimpleValidationExamples();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = ValidationException.class)
    public void givenBlankWhenValidateForNotBlankNumericAndThrowThrowsException() throws ValidationException {
        examples.validateForNotBlankNumericAndThrow(null);
    }

    @Test(expected = ValidationException.class)
    public void givenAlphaNumericWhenValidateForNotBlankNumericAndThrowThrowsException() throws ValidationException {
        examples.validateForNotBlankNumericAndThrow("abc");
    }

    @Test()
    public void givenNumericWhenValidateForNotBlankNumericAndThrowReturns() throws ValidationException {
        examples.validateForNotBlankNumericAndThrow("5");
    }

    @Test(expected = ValidationException.class)
    public void givenNumberOutSideRangeWhenValidateForNumberInRangeThrowsException() throws ValidationException {
        examples.validateForNumberInRange(15);
    }

    @Test()
    public void givenNumberInRangeWhenValidateForNumberInRangeReturn() throws ValidationException {
        examples.validateForNumberInRange(18);
    }

    @Test(expected = ValidationException.class)
    public void givenInvalidValueWhenValidateForValueInListOfValidValuesThrowsException() throws ValidationException {
        examples.validateForValueInListOfValidValues("Amsterdam");
    }

    @Test
    public void givenValidValueWhenValidateForValueInListOfValidValuesThrowsException() throws ValidationException {
        examples.validateForValueInListOfValidValues("Vienna");
    }

    @Test
    public void testValidateUsingResult() {
        assertThat(examples.validateUsingResult().isValid()).isTrue();
    }

    @Test
    public void testValidateThrowingAnErrorIfViolates() throws Exception {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("fileSize: value must be greater than 10");
        examples.validateThrowingAnErrorIfViolates();
    }

    @Test
    public void tesCombineMoreValidationsInASingleLine() throws Exception {
        examples.combineMoreValidationsInASingleLine();
    }
}