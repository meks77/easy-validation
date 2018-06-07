package at.meks.validation.validations.string;

import at.meks.validation.TestUtils;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.mockito.ArgumentCaptor;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.TestUtils.anySupplier;
import static at.meks.validation.TestUtils.assertSupplierValue;
import static at.meks.validation.TestUtils.getSupplierCaptor;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class StringValidationsTestHelper {

    private final CoreStringValidations coreValidations;
    private final AbstractValidationsTest<String> test;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    StringValidationsTestHelper(CoreStringValidations coreValidationsMock, AbstractValidationsTest<String> testInstance) {
        this.coreValidations = coreValidationsMock;
        this.test = testInstance;
    }

    void testLengthIsMoreThan(Function<Integer, Validation<String>> methodInvoker) {
        int minSize = 4;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getLengthIsMoreThanMessage(minSize);
        doReturn(test.getExpectedValidation()).when(coreValidations).lengthIsMoreThan(anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.apply(minSize);
        ArgumentCaptor<Supplier<Integer>> valuSupplierCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).lengthIsMoreThan(valuSupplierCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(minSize, valuSupplierCaptor);
    }

    void testLengthIsLessThan(Function<Integer, Validation<String>> methodInvoker) {
        int size = 4;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getLengthIsLessThanMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations).lengthIsLessThan(anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.apply(size);
        ArgumentCaptor<Supplier<Integer>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).lengthIsLessThan(valueCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(size, valueCaptor);
    }

    void testLengthIsBetween(IsLengthBetween methodInvoker) {
        int minSize = 4;
        int maxSize = 6;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getLengthIsBetweenMessage(minSize, maxSize);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .lengthIsBetween(anySupplier(), anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.isLengthBetween(minSize, maxSize);
        ArgumentCaptor<Supplier<Integer>> minValueCaptor = getSupplierCaptor();
        ArgumentCaptor<Supplier<Integer>> maxValueCaptor = getSupplierCaptor();
        //noinspection unchecked
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).lengthIsBetween(minValueCaptor.capture(),
                        maxValueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(minSize, minValueCaptor);
        assertSupplierValue(maxSize, maxValueCaptor);
    }

    void testHasLength(Function<Integer, Validation<String>> methodInvoker) {
        int length = 4;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getHasLengthMessage(length);
        doReturn(test.getExpectedValidation()).when(coreValidations).hasLength(anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.apply(length);
        ArgumentCaptor<Supplier<Integer>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).hasLength(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(length, valueCaptor);
    }

    void testContains(Function<String, Validation<String>> methodInvoker) {
        String searchedValue = "a";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getContainsMessage(searchedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations).contains(anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.apply(searchedValue);
        ArgumentCaptor<Supplier<String>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).contains(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(searchedValue, valueCaptor);
    }

    void testIsNotBlank(Supplier<Validation<String>> methodInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsNotBlankMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isNotBlank(any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).isNotBlank(errorDescCaptor.capture()));
    }

    void testIsInArray(Function<Supplier<String[]>, Validation<String>> methodInvoker) {
        String[] validValues = {"a", "b", "c"};
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsInListMessage(Arrays.asList(validValues));

        //noinspection unchecked
        doReturn(test.getExpectedValidation()).when(coreValidations).isInArray(any(Supplier.class), any(Supplier.class));
        Validation<String> validation = methodInvoker.apply(() -> validValues);
        test.assertForExpectedValidation(validation);
        ArgumentCaptor<Supplier<String[]>> validValueSupplierCaptor = TestUtils.getSupplierCaptor();
        ArgumentCaptor<Supplier<ErrorDescription>> errorDescSupplierCaptor = TestUtils.getSupplierCaptor();
        verify(coreValidations).isInArray(validValueSupplierCaptor.capture(), errorDescSupplierCaptor.capture());
        test.assertErrorDesc(errorDescSupplierCaptor.getValue().get());
        assertThat(validValueSupplierCaptor.getValue().get()).isSameAs(validValues);
    }

    void testIsInList(Function<Supplier<Collection<String>>, Validation<String>> methodInvoker) {
        Set<String> validValues = new HashSet<>(Arrays.asList("a", "b", "c"));
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsInListMessage(validValues);
        //noinspection unchecked
        doReturn(test.getExpectedValidation()).when(coreValidations).isInList(any(Supplier.class), any(Supplier.class));
        Validation<String> validation = methodInvoker.apply(() -> validValues);
        test.assertForExpectedValidation(validation);
        ArgumentCaptor<Supplier<Collection<String>>> validValueSupplierCaptor = TestUtils.getSupplierCaptor();
        ArgumentCaptor<Supplier<ErrorDescription>> errorDescSupplierCaptor = TestUtils.getSupplierCaptor();
        verify(coreValidations).isInList(validValueSupplierCaptor.capture(), errorDescSupplierCaptor.capture());
        test.assertErrorDesc(errorDescSupplierCaptor.getValue().get());
        assertThat(validValueSupplierCaptor.getValue().get()).isSameAs(validValues);
    }

    void testIsDate(Function<DateTimeFormatter, Validation<String>> methodInvoker) {
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsDateMessage(dateTimeFormatter);
        doReturn(test.getExpectedValidation()).when(coreValidations).isDate(anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.apply(dateTimeFormatter);
        ArgumentCaptor<Supplier<DateTimeFormatter>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).isDate(valueCaptor.capture(), errorDescCaptor.capture()));
        assertSupplierValue(dateTimeFormatter, valueCaptor);
    }

    void testIsNumeric(Supplier<Validation<String>> methodInvoker) {
        //noinspection ResultOfMethodCallIgnored
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsNumericMessage();
        doReturn(test.getExpectedValidation()).when(coreValidations).isNumeric(any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.get();
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).isNumeric(errorDescCaptor.capture()));
    }

    void testContainsNotOnly(Function<String, Validation<String>> methodInvoker) {
        String searchedValue = "a";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getContainsNotOnlyMessage(searchedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations).containsNotOnly(anySupplier(), anySupplier());
        Validation<String> validation = methodInvoker.apply(searchedValue);
        ArgumentCaptor<Supplier<String>> valueCaptor = getSupplierCaptor();
        test.doAssertionsAndVerificationsWithSupplier(validation,
                errorDescCaptor -> verify(coreValidations).containsNotOnly(valueCaptor.capture(),
                        errorDescCaptor.capture()));
        assertSupplierValue(searchedValue, valueCaptor);
    }
}
