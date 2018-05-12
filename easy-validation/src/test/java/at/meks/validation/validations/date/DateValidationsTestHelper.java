package at.meks.validation.validations.date;

import at.meks.validation.TestUtils;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static at.meks.validation.TestUtils.assertSupplierValue;
import static at.meks.validation.TestUtils.getSupplierCaptor;
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
        doReturn(test.getExpectedValidation()).when(coreValidations).isDateAfter(anySupplier(), anySupplier());

        Validation<LocalDateTime> validation = methodeInvoker.apply(dateBefore);

        ArgumentCaptor<Supplier<LocalDateTime>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).isDateAfter(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(dateBefore, supplierCaptor);
    }
}
