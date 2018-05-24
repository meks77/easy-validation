package at.meks.validation.validations.date;

import at.meks.validation.validations.AbstractValidationsTest;

import java.time.LocalDateTime;

class DateValidationsTestHelper {

    private final CoreDateValidations coreValidations;
    private final AbstractValidationsTest<LocalDateTime> test;

    DateValidationsTestHelper(CoreDateValidations coreValidationsMock, AbstractValidationsTest<LocalDateTime> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

//    void testIsLocalDateAfter(Function<LocalDateTime, Validation<LocalDateTime>> methodInvoker) {
//        LocalDateTime dateBefore = LocalDateTime.of(2010, 5, 1, 10, 5, 10);
//        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsDateAfterMessage(dateBefore);
//        doReturn(test.getExpectedValidation()).when(coreValidations).isDateAfter(anySupplier(), anySupplier());
//
//        Validation<LocalDateTime> validation = methodInvoker.apply(dateBefore);
//
//        ArgumentCaptor<Supplier<LocalDateTime>> supplierCaptor = getSupplierCaptor();
//        test.doAssertionsAndVerificationsWithSupplier(validation,
//                errorDescCaptor ->  verify(coreValidations).isDateAfter(supplierCaptor.capture(),
//                        errorDescCaptor.capture()));
//        assertSupplierValue(dateBefore, supplierCaptor);
//    }
}
