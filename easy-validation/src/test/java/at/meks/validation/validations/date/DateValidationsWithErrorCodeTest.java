package at.meks.validation.validations.date;

import at.meks.validation.validations.AbstractCodeValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

import static org.fest.assertions.api.Assertions.assertThat;

public class DateValidationsWithErrorCodeTest extends AbstractCodeValidationsTest<LocalDateTime> {

    @Mock
    private CoreDateValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return DateValidationsWithErrorCode.class;
    }

    @Override
    protected Object getCoreValidations() {
        return coreValidations;
    }

    private DateValidationsTestHelper testHelper;

    @Before
    public void initTestHelper() {
        testHelper = new DateValidationsTestHelper(coreValidations, this);
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<DateValidationsWithErrorCode> constructor = DateValidationsWithErrorCode.class
                .getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testIsDateAfter() {
        testHelper.testIsDateAfter(dateAfter ->  DateValidationsWithErrorCode.isDateAfter(dateAfter, EXPECTED_CODE));
    }

}