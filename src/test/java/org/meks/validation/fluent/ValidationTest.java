package org.meks.validation.fluent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.meks.validation.fluent.result.ValidationException;

import java.util.Collections;
import java.util.List;

import static org.meks.validation.fluent.DeserializedPersonInfoBuilder.aDeserializedPersonInfo;
import static org.meks.validation.fluent.StringValidations.isInList;
import static org.meks.validation.fluent.StringValidations.isNotBlank;

public class ValidationTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("myTestedParam: must be in valid list: [a, b, c]");
        isNotBlank().and(isInList(this::getValidValues)).test(() -> "notEmpty").throwIfInvalid("myTestedParam");
    }

    private String[] getValidValues() {
        return new String[] {"a", "b", "c"};
    }

    private String getValidatedValue() {
        return "validatedValue";
    }

    @Test
    public void testComplexValidation() throws ValidationException {
        PersonInfoValidator validator = new PersonInfoValidator();
        validator.validatePerson(aDeserializedPersonInfo().withAccount("132456").withFirstName("xx").withName("yyy").withPostalCode("54321").build());


    }
}
