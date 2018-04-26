package org.meks.validation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ValidationResult;
import org.mockito.Mockito;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

public class SimpleValidationTest {

    private static final String VALIDATED_VALUE = "myString";

    @Test
    public void givenPredicateWhenWithThenIsAvailableInValidation() throws Exception {
        @SuppressWarnings("unchecked")
        Predicate<Object> expectedPredicate = Mockito.mock(Predicate.class);
        SimpleValidation<Object> validation = SimpleValidation.from(expectedPredicate, () -> withMessage("xxx"));
        assertThat(FieldUtils.readField(validation, "predicate", true)).isSameAs(expectedPredicate);
    }

    @Test
    public void givenErrorDescriptionWhenWithThenIsAvailableInValidation() throws Exception {
        Supplier<ErrorDescription> expectedErrorDescription = () -> withMessage("xxx");
        @SuppressWarnings("unchecked")
        SimpleValidation<Object> validation = SimpleValidation.from(Mockito.mock(Predicate.class), expectedErrorDescription);
        assertThat(FieldUtils.readField(validation, "onErrorMessage", true)).isSameAs(expectedErrorDescription);
    }

    @Test
    public void givenPredicateReturnsTrueWhenTestThenValidationResultIsValid() {
        Predicate<String> expectedPredicate = mockPredicate(true);
        ValidationResult result = SimpleValidation.from(expectedPredicate, () -> withMessage("xxx"))
                .test(VALIDATED_VALUE);
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void givenPredicateReturnsFalseWhenTestThenValidationResultIsNotValid() {
        Predicate<String> expectedPredicate = mockPredicate(false);
        ValidationResult result = SimpleValidation.from(expectedPredicate, () -> withMessage("xxx"))
                .test(VALIDATED_VALUE);
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void givenPredicateReturnsFalseWhenTestThenValidationResultContainsErrorDescription() throws Exception {
        ErrorDescription expectedErrorDescription = withMessage("xxx");
        ValidationResult result = SimpleValidation.from(mockPredicate(false), () -> expectedErrorDescription)
                .test(VALIDATED_VALUE);
        assertThat(FieldUtils.readField(result, "errorDescription", true)).isSameAs(expectedErrorDescription);
    }

    private Predicate<String> mockPredicate(boolean b) {
        @SuppressWarnings("unchecked")
        Predicate<String> expectedPredicate = Mockito.mock(Predicate.class);
        Mockito.doReturn(b).when(expectedPredicate).test(VALIDATED_VALUE);
        return expectedPredicate;
    }


}