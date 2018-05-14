package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Function;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class ListValidationsTest extends AbstractValidationsTest<List<String>> {

    private static final String TESTED_VALUE = "testedValue";
    private static final String ERROR_CONTAINS_ONLY = "list must contain only " + TESTED_VALUE;

    @Mock
    private CoreListValidations coreValidations;

    private ListValidationsTestHelper testHelper;

    @Override
    protected Class<?> getTestedClass() {
        return ListValidations.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<ListValidations> constructor = ListValidations.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Before
    public void initTestHelper() {
        testHelper = new ListValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testContainsOnly() {
        testHelper.testContainsOnly(ListValidations::containsOnly);
    }

    @Test
    public void testContains() {
        testHelper.testContains(ListValidations::contains);
    }

    @Test
    public void testDoesNotContain() {
        testHelper.testDoesNotContain(ListValidations::doesNotContain);
    }

    @Test
    public void testIsNotEmpty() {
        testHelper.testIsNotEmpty(ListValidations::isNotEmpty);
    }

    @Test
    public void testIsEmpty() {
        testHelper.testIsEmpty(ListValidations::isEmpty);
    }

    @Test
    public void testHasSize() {
        testHelper.testHasSize(ListValidations::hasSize);
    }

    @Test
    public void testHasMinSize() {
        testHelper.testHasMinSize(ListValidations::hasMinSize);
    }

    @Test
    public void testHasMaxSize() {
        testHelper.testHasMaxSize(ListValidations::hasMaxSize);
    }

    @Test
    public void testOnProperty() {
        @SuppressWarnings("unchecked")
        Function<String, String> mapperMock = Mockito.mock(Function.class);
        doReturn(getExpectedValidation()).when(coreValidations).onProperty(mapperMock, getExpectedValidation());
        Validation<List<String>> validation = ListValidations.onProperty(mapperMock, getExpectedValidation());
        assertThat(validation).isSameAs(getExpectedValidation());
    }

    @Test
    public void givenValidationWhenForTypeThenSameValidationIsReturned() {
        doReturn(getExpectedValidation()).when(coreValidations).forType(String.class, getExpectedValidation());
        assertThat(ListValidations.forType(String.class, getExpectedValidation())).isSameAs(getExpectedValidation());
    }
}