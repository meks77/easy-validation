package at.meks.validation.validations.date;

import at.meks.validation.validations.AbstractCodeValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

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
    public void testIsDateAfter() {
        testHelper.testIsDateAfter(dateAfter ->  DateValidationsWithErrorCode.isDateAfter(dateAfter, EXPECTED_CODE));
    }

}