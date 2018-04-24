package org.meks.validation.fluent;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.meks.validation.fluent.result.ErrorDescription;
import org.meks.validation.fluent.result.ErrorDescriptionBuilder;
import org.meks.validation.fluent.result.ValidationResult;
import org.mockito.Mockito;

import java.util.function.Predicate;

import static org.fest.assertions.api.Assertions.assertThat;

public class SimpleValidationTest {

    private static final String VALIDATED_VALUE = "myString";

    @Test
    public void givenPredicateWhenWithThenIsAvailableInValidation() throws Exception {
        @SuppressWarnings("unchecked")
        Predicate<Object> expectedPredicate = Mockito.mock(Predicate.class);
        SimpleValidation<Object> validation = SimpleValidation.from(expectedPredicate, ErrorDescriptionBuilder.withMessage("xxx"));
        assertThat(FieldUtils.readField(validation, "predicate", true)).isSameAs(expectedPredicate);
    }

    @Test
    public void givenErrorDescriptionWhenWithThenIsAvailableInValidation() throws Exception {
        ErrorDescription expectedErrorDescription = ErrorDescriptionBuilder.withMessage("xxx");
        @SuppressWarnings("unchecked")
        SimpleValidation<Object> validation = SimpleValidation.from(Mockito.mock(Predicate.class), expectedErrorDescription);
        assertThat(FieldUtils.readField(validation, "onErrorMessage", true)).isSameAs(expectedErrorDescription);
    }

    @Test
    public void givenPredicateReturnsTrueWhenTestThenValidationResultIsValid() {
        Predicate<String> expectedPredicate = mockPredicate(true);
        ValidationResult result = SimpleValidation.from(expectedPredicate, ErrorDescriptionBuilder.withMessage("xxx"))
                .test(() -> VALIDATED_VALUE);
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void givenPredicateReturnsFalseWhenTestThenValidationResultIsNotValid() {
        Predicate<String> expectedPredicate = mockPredicate(false);
        ValidationResult result = SimpleValidation.from(expectedPredicate, ErrorDescriptionBuilder.withMessage("xxx"))
                .test(() -> VALIDATED_VALUE);
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void givenPredicateReturnsFalseWhenTestThenValidationResultContainsErrorDescription() throws Exception {
        ErrorDescription expectedErrorDescription = ErrorDescriptionBuilder.withMessage("xxx");
        ValidationResult result = SimpleValidation.from(mockPredicate(false), expectedErrorDescription)
                .test(() -> VALIDATED_VALUE);
        assertThat(FieldUtils.readField(result, "errorDescription", true)).isSameAs(expectedErrorDescription);
    }

    private Predicate<String> mockPredicate(boolean b) {
        @SuppressWarnings("unchecked")
        Predicate<String> expectedPredicate = Mockito.mock(Predicate.class);
        Mockito.doReturn(b).when(expectedPredicate).test(VALIDATED_VALUE);
        return expectedPredicate;
    }


}