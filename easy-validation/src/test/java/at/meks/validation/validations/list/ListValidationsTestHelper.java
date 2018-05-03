package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class ListValidationsTestHelper {

    private final CoreListValidations coreValidations;
    private final AbstractValidationsTest<List<String>> test;

    ListValidationsTestHelper(CoreListValidations coreValidationsMock, AbstractValidationsTest<List<String>> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testContainsOnly(Function<String, Validation<List<String>>> methodeInvoker) {
        String containedValue = "A";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListContainsOnlyMessage(containedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations).containsOnly(eq(containedValue), any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.apply(containedValue);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).containsOnly(eq(containedValue), errorDescCaptor.capture()));
    }

    void testContains(Function<String, Validation<List<String>>> methodeInvoker) {
        String containedValue = "A";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListContainsMessage(containedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations).contains(eq(containedValue), any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.apply(containedValue);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).contains(eq(containedValue), errorDescCaptor.capture()));
    }

    void testDoesNotContain(Function<String, Validation<List<String>>> methodeInvoker) {
        String containedValue = "A";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListDoesNotContainMessage(containedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations).doesNotContain(eq(containedValue), any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.apply(containedValue);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).doesNotContain(eq(containedValue), errorDescCaptor.capture()));
    }

    void testIsNotEmpty(Supplier<Validation<List<String>>> methodeInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListIsNotEmptyMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isNotEmpty(any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isNotEmpty(errorDescCaptor.capture()));
    }

    void testIsEmpty(Supplier<Validation<List<String>>> methodeInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListIsEmptyMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isEmpty(any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).isEmpty(errorDescCaptor.capture()));
    }

    void testHasSize(Function<Integer, Validation<List<String>>> methodeInvoker) {
        int size = 5;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListHasSizeMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations).hasSize(eq(size), any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.apply(size);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).hasSize(eq(size), errorDescCaptor.capture()));
    }

    void testHasMinSize(Function<Integer, Validation<List<String>>> methodeInvoker) {
        int size = 5;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListHasMinSizeMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations).hasMinSize(eq(size), any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.apply(size);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).hasMinSize(eq(size), errorDescCaptor.capture()));
    }

    void testHasMaxSize(Function<Integer, Validation<List<String>>> methodeInvoker) {
        int size = 5;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListHasMaxSizeMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations).hasMaxSize(eq(size), any(ErrorDescription.class));

        Validation<List<String>> validation = methodeInvoker.apply(size);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor ->  verify(coreValidations).hasMaxSize(eq(size), errorDescCaptor.capture()));
    }

}
