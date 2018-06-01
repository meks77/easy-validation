package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static at.meks.validation.TestUtils.assertSupplierValue;
import static at.meks.validation.TestUtils.getSupplierCaptor;
import static org.mockito.Mockito.any;
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
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .containsOnly(anySupplier(), anySupplier());

        Validation<List<String>> validation = methodeInvoker.apply(containedValue);

        ArgumentCaptor<Supplier<String>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).containsOnly(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(containedValue, supplierCaptor);
    }

    void testContains(Function<String, Validation<List<String>>> methodeInvoker) {
        String containedValue = "A";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListContainsMessage(containedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .contains(anySupplier(), anySupplier());

        Validation<List<String>> validation = methodeInvoker.apply(containedValue);

        ArgumentCaptor<Supplier<String>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).contains(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(containedValue, supplierCaptor);
    }

    void testDoesNotContain(Function<String, Validation<List<String>>> methodeInvoker) {
        String containedValue = "A";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListDoesNotContainMessage(containedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .doesNotContain(anySupplier(), anySupplier());

        Validation<List<String>> validation = methodeInvoker.apply(containedValue);

        ArgumentCaptor<Supplier<String>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).doesNotContain(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(containedValue, supplierCaptor);
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
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .hasSize(anySupplier(), anySupplier());

        Validation<List<String>> validation = methodeInvoker.apply(size);
        ArgumentCaptor<Supplier<Integer>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).hasSize(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(size, supplierCaptor);
    }

    void testHasMinSize(Function<Integer, Validation<List<String>>> methodeInvoker) {
        int size = 5;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListHasMinSizeMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .hasMinSize(anySupplier(), anySupplier());

        Validation<List<String>> validation = methodeInvoker.apply(size);

        ArgumentCaptor<Supplier<Integer>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).hasMinSize(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(size, supplierCaptor);
    }

    void testHasMaxSize(Function<Integer, Validation<List<String>>> methodeInvoker) {
        int size = 5;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getListHasMaxSizeMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .hasMaxSize(anySupplier(), anySupplier());

        Validation<List<String>> validation = methodeInvoker.apply(size);

        ArgumentCaptor<Supplier<Integer>> supplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor ->  verify(coreValidations).hasMaxSize(supplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(size, supplierCaptor);
    }

}
