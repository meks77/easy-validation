package at.meks.validation.validations.date;

import at.meks.validation.validations.AbstractValidationsTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

public class DateValidationsTest extends AbstractValidationsTest<LocalDateTime> {

    @Mock
    private CoreDateValidations coreValidations;

    @Override
    protected Class<?> getTestedClass() {
        return DateValidations.class;
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
        testHelper.testIsDateAfter(DateValidations::isDateAfter);
    }

}