package at.meks.validation.validations.number;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;

import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class NumberValidationsTestHelper {

    private final CoreNumberValidations coreValidations;
    private final AbstractValidationsTest<Number> test;

    NumberValidationsTestHelper(CoreNumberValidations coreValidationsMock, AbstractValidationsTest<Number> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testIsInt(Supplier<Validation<Number>> methodInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsIntMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isInt(any(ErrorDescription.class));

        Validation<Number> validation = methodInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isInt(errorDescCaptor.capture()));
    }


    void testIsByte(Supplier<Validation<Number>> methodInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsByteMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isByte(any(ErrorDescription.class));

        Validation<Number> validation = methodInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isByte(errorDescCaptor.capture()));
    }

    void testIsShort(Supplier<Validation<Number>> methodInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsShortMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isShort(any(ErrorDescription.class));

        Validation<Number> validation = methodInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isShort(errorDescCaptor.capture()));
    }
}
