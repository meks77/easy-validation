package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.validations.AbstractErrorCodeValidationsTest;
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

public class ListValidationsWithErrorCodeTest extends AbstractErrorCodeValidationsTest<List<String>> {

    @Mock
    private CoreListValidations coreValidations;

    private ListValidationsTestHelper testHelper;

    @Override
    protected Class<?> getTestedClass() {
        return ListValidationsWithErrorCode.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    @Before
    public void initTestHelper() {
        testHelper = new ListValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<ListValidationsWithErrorCode> constructor = ListValidationsWithErrorCode.class
                .getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testContainsOnly() {
        testHelper.testContainsOnly(s -> ListValidationsWithErrorCode.containsOnly(s, EXPECTED_CODE));
    }

    @Test
    public void testContains() {
        testHelper.testContains(s -> ListValidationsWithErrorCode.contains(s, EXPECTED_CODE));
    }

    @Test
    public void testDoesNotContain() {
        testHelper.testDoesNotContain(s -> ListValidationsWithErrorCode.doesNotContain(s, EXPECTED_CODE));
    }

    @Test
    public void testIsNotEmpty() {
        testHelper.testIsNotEmpty(() -> ListValidationsWithErrorCode.isNotEmpty(EXPECTED_CODE));
    }

    @Test
    public void testIsEmpty() {
        testHelper.testIsEmpty(() -> ListValidationsWithErrorCode.isEmpty(EXPECTED_CODE));
    }

    @Test
    public void testHasSize() {
        testHelper.testHasSize(size -> ListValidationsWithErrorCode.hasSize(size, EXPECTED_CODE));
    }

    @Test
    public void testHasMinSize() {
        testHelper.testHasMinSize(size -> ListValidationsWithErrorCode.hasMinSize(size, EXPECTED_CODE));
    }

    @Test
    public void testHasMaxSize() {
        testHelper.testHasMaxSize(size -> ListValidationsWithErrorCode.hasMaxSize(size, EXPECTED_CODE));
    }

    @Test
    public void testOnProperty() {
        @SuppressWarnings("unchecked")
        Function<String, String> mapperMock = Mockito.mock(Function.class);
        doReturn(getExpectedValidation()).when(coreValidations).onProperty(mapperMock, getExpectedValidation());
        Validation<List<String>> validation = ListValidationsWithErrorCode.onProperty(mapperMock, getExpectedValidation());
        assertThat(validation).isSameAs(getExpectedValidation());
    }

    @Test
    public void givenValidationWhenForTypeThenSameValidationIsReturned() {
        doReturn(getExpectedValidation()).when(coreValidations).forType(String.class, getExpectedValidation());
        assertThat(ListValidationsWithErrorCode.forType(String.class, getExpectedValidation())).isSameAs(getExpectedValidation());
    }
}