package org.meks.validation.fluent.list;

import org.junit.Test;
import org.meks.validation.fluent.Validation;
import org.meks.validation.fluent.result.ValidationResult;
import org.mockito.Mockito;

import java.awt.Point;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.meks.validation.fluent.TestUtils.assertErrorResult;
import static org.meks.validation.fluent.TestUtils.assertValidResult;
import static org.meks.validation.fluent.list.ListValidations.contains;
import static org.meks.validation.fluent.list.ListValidations.containsOnly;
import static org.meks.validation.fluent.list.ListValidations.doesNotContain;
import static org.meks.validation.fluent.list.ListValidations.forType;
import static org.meks.validation.fluent.list.ListValidations.hasMaxSize;
import static org.meks.validation.fluent.list.ListValidations.hasMinSize;
import static org.meks.validation.fluent.list.ListValidations.hasSize;
import static org.meks.validation.fluent.list.ListValidations.isEmpty;
import static org.meks.validation.fluent.list.ListValidations.isNotEmpty;
import static org.meks.validation.fluent.list.ListValidations.onProperty;
import static org.mockito.Mockito.mock;

public class ListValidationsTest {

    private static final String TESTED_VALUE = "testedValue";
    private static final String ERROR_CONTAINS_ONLY = "list must contain only " + TESTED_VALUE;

    @Test
    public void givenListContainingOnlyTestedValueWhenContainsOnlyReturnsValidResult() {
        assertValidResult(containsOnly(TESTED_VALUE).test(() -> asList(TESTED_VALUE, TESTED_VALUE)));
    }

    @Test
    public void givenListWithoutContainedValueWhenContainsOnlyReturnsErrorResult() {
        ValidationResult result = containsOnly(TESTED_VALUE).test(() -> singletonList("somethingElse"));
        assertErrorResult(result, ERROR_CONTAINS_ONLY);
    }

    @Test
    public void givenListContainingNotOnlyTestedValueWhenContainsOnlyReturnsErrorResult() {
        ValidationResult result = containsOnly(TESTED_VALUE).test(() -> asList(TESTED_VALUE, "somethingElse"));
        assertErrorResult(result, ERROR_CONTAINS_ONLY);
    }

    @Test
    public void givenListContainingTestedValueWhenContainsReturnsValidResult() {
        assertValidResult(contains(TESTED_VALUE).test(() -> asList("firstValue", TESTED_VALUE, "thirdValue")));
    }

    @Test
    public void givenListNotContainingTestedValueWhenContainsReturnsErrorResult() {
        ValidationResult result = contains(TESTED_VALUE).test(() -> asList("first", "second", "third"));
        assertErrorResult(result, "list must contain " + TESTED_VALUE);
    }

    @Test
    public void givenListNotContainingTestedValueWhenDoesNotContainReturnsValidResult() {
        assertValidResult(doesNotContain(TESTED_VALUE).test(() -> singletonList("other")));
    }

    @Test
    public void givenListContainingTestedValueWhenDoesNotContainReturnsErrorResult() {
        ValidationResult result = doesNotContain(TESTED_VALUE).test(() -> asList("first", TESTED_VALUE, "third"));
        assertErrorResult(result, "list mustn't contain " + TESTED_VALUE);
    }

    @Test
    public void givenNotEmptyListWhenIsNotEmptyReturnsValidResult() {
        assertValidResult(isNotEmpty().test(() -> singletonList("first")));
    }

    @Test
    public void givenEmptyListWhenIsNotEmptyReturnsValidResult() {
        assertErrorResult(isNotEmpty().test(Collections::emptyList), "list mustn't be empty");
    }

    @Test
    public void givenEmptyListWhenIsEmptyReturnsValidResult() {
        assertValidResult(isEmpty().test(Collections::emptyList));
    }

    @Test
    public void givenNotEmptyListWhenIsEmptyReturnsValidResult() {
        assertErrorResult(isEmpty().test(() -> singletonList("first")), "list must be empty");
    }

    @Test
    public void givenListSize5WhenHasSize5RturnsValidResult() {
        assertValidResult(hasSize(5).test(() -> asList("one", "two", "three", "four", "five")));
    }

    @Test
    public void givenListSize2WhenHasSize5RturnsErrorResult() {
        assertErrorResult(hasSize(5).test(() -> asList("one", "two", "three")), "size of list must be 5");
    }

    @Test
    public void givenListSize3WhenHasMinSize3ReturnsValidResult() {
        assertValidResult(hasMinSize(3).test(() -> asList("one", "two", "three")));
    }

    @Test
    public void givenListSize4WhenHasMinSize3ReturnsValidResult() {
        assertValidResult(hasMinSize(3).test(() -> asList("one", "two", "three", "four")));
    }

    @Test
    public void givenListSize2WhenHasMinSize3ReturnsErrorResult() {
        assertErrorResult(hasMinSize(3).test(() -> asList("one", "two")), "size of list must be at least 3");
    }

    @Test
    public void givenListSize3WhenHasMaxSize3ReturnsValidResult() {
        assertValidResult(hasMaxSize(3).test(() -> asList("one", "two", "three")));
    }

    @Test
    public void givenListSize2WhenHasMaxSize3ReturnsValidResult() {
        assertValidResult(hasMaxSize(3).test(() -> asList("one", "two")));
    }

    @Test
    public void givenListSize4WhenHasMaxSize3ReturnsErrorResult() {
        ValidationResult result = hasMaxSize(3).test(() -> asList("one", "two", "three", "four"));
        assertErrorResult(result, "size of list mustn't be greater than 3");
    }

    @Test
    public void givenArgsWhenOnPropertyThenValidationWithSameInstancesAsFieldsIsReturned() {
        @SuppressWarnings("unchecked")
        Function<Point, String> mapperMock = Mockito.mock(Function.class);
        @SuppressWarnings("unchecked")
        Validation<List<String>> validationMock = Mockito.mock(Validation.class);
        Validation<List<Point>> validation = onProperty(mapperMock, validationMock);
        Validation<List<Point>> expectedValidation = ListPropertyValidationImpl.onProperty(mapperMock, validationMock);
        assertThat(validation).isEqualsToByComparingFields(expectedValidation);
    }

    @Test
    public void givenValidationWhenForTypeThenSameValidationIsReturned() {
        @SuppressWarnings("unchecked") 
        Validation<List<String>> expectedValidation = mock(Validation.class);
        assertThat(forType(String.class, expectedValidation)).isSameAs(expectedValidation);
    }
}