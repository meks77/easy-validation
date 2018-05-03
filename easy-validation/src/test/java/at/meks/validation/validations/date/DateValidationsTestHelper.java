package at.meks.validation.validations.date;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;

import java.time.LocalDateTime;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class DateValidationsTestHelper {

    private final CoreDateValidations coreValidations;
    private final AbstractValidationsTest<LocalDateTime> test;

    DateValidationsTestHelper(CoreDateValidations coreValidationsMock, AbstractValidationsTest<LocalDateTime> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testIsDateAfter(Function<LocalDateTime, Validation<LocalDateTime>> methodeInvoker) {
        LocalDateTime dateBefore = LocalDateTime.of(2010, 5, 1, 10, 5, 10);
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsDateAfterMessage(dateBefore);
        doReturn(test.getExpectedValidation()).when(coreValidations).isDateAfter(same(dateBefore), any(ErrorDescription.class));

        Validation<LocalDateTime> validation = methodeInvoker.apply(dateBefore);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isDateAfter(same(dateBefore), errorDescCaptor.capture()));
    }
}
