package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractCoreValidationsTest;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.Point;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CoreListValidationsTest extends AbstractCoreValidationsTest {

    private static final String TESTED_VALUE = "testedValue";

    private CoreListValidations validations = new CoreListValidations();

    @Test
    public void givenListContainingOnlyTestedValueWhenContainsOnlyReturnsValidResult() {
        assertValidResult(validations.containsOnly(() -> TESTED_VALUE, () -> errorDescription)
                .test(asList(TESTED_VALUE, TESTED_VALUE)));
    }

    @Test
    public void givenListWithoutContainedValueWhenContainsOnlyReturnsErrorResult() {
        assertErrorResult(validations.containsOnly(() -> TESTED_VALUE, () -> errorDescription)
                .test(singletonList("somethingElse")));
    }

    @Test
    public void givenListContainingNotOnlyTestedValueWhenContainsOnlyReturnsErrorResult() {
        assertErrorResult(validations.containsOnly(() -> TESTED_VALUE, () -> errorDescription)
                .test(asList(TESTED_VALUE, "somethingElse")));
    }

    @Test
    public void givenListContainingTestedValueWhenContainsReturnsValidResult() {
        assertValidResult(validations.contains(() -> TESTED_VALUE, () -> errorDescription)
                .test(asList("firstValue", TESTED_VALUE, "thirdValue")));
    }

    @Test
    public void givenListNotContainingTestedValueWhenContainsReturnsErrorResult() {
        assertErrorResult(validations.contains(() -> TESTED_VALUE, () -> errorDescription)
                .test(asList("first", "second", "third")));
    }

    @Test
    public void givenListNotContainingTestedValueWhenDoesNotContainReturnsValidResult() {
        assertValidResult(validations.doesNotContain(() -> TESTED_VALUE, () -> errorDescription)
                .test(singletonList("other")));
    }

    @Test
    public void givenListContainingTestedValueWhenDoesNotContainReturnsErrorResult() {
        assertErrorResult(validations.doesNotContain(() -> TESTED_VALUE, () -> errorDescription)
                .test(asList("first", TESTED_VALUE, "third")));
    }

    @Test
    public void givenNotEmptyListWhenIsNotEmptyReturnsValidResult() {
        assertValidResult(validations.isNotEmpty(errorDescription).test(singletonList("first")));
    }

    @Test
    public void givenEmptyListWhenIsNotEmptyReturnsErrorResult() {
        assertErrorResult(validations.isNotEmpty(errorDescription).test(Collections.emptyList()));
    }

    @Test
    public void givenNullWhenIsNotEmptyReturnsErrorResult() {
        assertErrorResult(validations.isNotEmpty(errorDescription).test(null));
    }

    @Test
    public void givenEmptyListWhenIsEmptyReturnsValidResultResult() {
        assertValidResult(validations.isEmpty(errorDescription).test(Collections.emptyList()));
    }

    @Test
    public void givenNotEmptyListWhenIsEmptyReturnsErrorResult() {
        assertErrorResult(validations.isEmpty(errorDescription).test(singletonList("first")));
    }

    @Test
    public void givenNullWhenIsEmptyReturnError() {
        assertErrorResult(validations.isEmpty(errorDescription).test(null));
    }

    @Test
    public void givenListSize5WhenHasSize5RturnsValidResult() {
        assertValidResult(validations.hasSize(() -> 5, () -> errorDescription)
                .test(asList("one", "two", "three", "four", "five")));
    }

    @Test
    public void givenListSize2WhenHasSize5RturnsErrorResult() {
        assertErrorResult(validations.hasSize(() -> 5, () -> errorDescription).test(asList("one", "two", "three")));
    }

    @Test
    public void givenNullWhenHasSizeReturnsErrorResult() {
        assertErrorResult(validations.hasSize(() -> 5, () -> errorDescription).test(null));
    }

    @Test
    public void givenListSize3WhenHasMinSize3ReturnsValidResult() {
        assertValidResult(validations.hasMinSize(() -> 3, () -> errorDescription).test(asList("one", "two", "three")));
    }

    @Test
    public void givenListSize4WhenHasMinSize3ReturnsValidResult() {
        assertValidResult(validations.hasMinSize(() -> 3, () -> errorDescription)
                .test(asList("one", "two", "three", "four")));
    }

    @Test
    public void givenListSize2WhenHasMinSize3ReturnsErrorResult() {
        assertErrorResult(validations.hasMinSize(() -> 3, () -> errorDescription).test(asList("one", "two")));
    }

    @Test
    public void givenNullWhenHasMinSizeReturnsErrorResult() {
        assertErrorResult(validations.hasMinSize(() -> 3, () -> errorDescription).test(null));
    }

    @Test
    public void givenListSize3WhenHasMaxSize3ReturnsValidResult() {
        assertValidResult(validations.hasMaxSize(() -> 3, () -> errorDescription).test(asList("one", "two", "three")));
    }

    @Test
    public void givenListSize2WhenHasMaxSize3ReturnsValidResult() {
        assertValidResult(validations.hasMaxSize(() -> 3, () -> errorDescription).test(asList("one", "two")));
    }

    @Test
    public void givenListSize4WhenHasMaxSize3ReturnsErrorResult() {
        assertErrorResult(validations.hasMaxSize(() -> 3, () -> errorDescription)
                .test(asList("one", "two", "three", "four")));
    }

    @Test
    public void givenNullWhenHasMaxSizeReturnsErrorResult() {
        assertErrorResult(validations.hasMaxSize(() -> 3, () -> errorDescription).test(null));

    }

    @Test
    public void givenArgsWhenOnPropertyThenValidationWithSameInstancesAsFieldsIsReturned() {
        @SuppressWarnings("unchecked")
        Function<Point, String> mapperMock = Mockito.mock(Function.class);
        @SuppressWarnings("unchecked")
        Validation<List<String>> validationMock = Mockito.mock(Validation.class);
        Validation<List<Point>> validation = validations.onProperty(mapperMock, validationMock);
        Validation<List<Point>> expectedValidation = ListPropertyValidationImpl.onProperty(mapperMock, validationMock);
        assertThat(validation).isEqualsToByComparingFields(expectedValidation);
    }

    @Test
    public void givenValidationWhenForTypeThenSameValidationIsReturned() {
        @SuppressWarnings("unchecked") 
        Validation<List<String>> expectedValidation = mock(Validation.class);
        assertThat(validations.forType(String.class, expectedValidation)).isSameAs(expectedValidation);
    }
}