package at.meks.validation.validations.string;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.validations.AbstractValidationsTest;
import org.apache.commons.lang3.Range;
import org.mockito.ArgumentCaptor;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
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
        doReturn(test.getExpectedValidation()).when(coreValidations).lengthIsMoreThan(eq(minSize), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(minSize);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).lengthIsMoreThan(eq(minSize), errorDescCaptor.capture()));
    }

    void testLengthIsLessThan(Function<Integer, Validation<String>> methodInvoker) {
        int size = 4;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getLengthIsLessThanMessage(size);
        doReturn(test.getExpectedValidation()).when(coreValidations).lengthIsLessThan(eq(size), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(size);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).lengthIsLessThan(eq(size), errorDescCaptor.capture()));
    }

    void testLengthIsBetween(Function<Range<Integer>, Validation<String>> methodInvoker) {
        int minSize = 4;
        int maxSize = 6;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getLengthIsLessThanMessage(minSize);
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getLengthIsMoreThanMessage(maxSize);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .lengthIsBetween(eq(minSize), eq(maxSize),
                        any(ErrorDescription.class), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(Range.between(minSize, maxSize));
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).lengthIsBetween(eq(minSize), eq(maxSize),
                        errorDescCaptor.capture(), any(ErrorDescription.class)));
    }

    void testHasLength(Function<Integer, Validation<String>> methodInvoker) {
        int length = 4;
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getHasLenghtMessage(length);
        doReturn(test.getExpectedValidation()).when(coreValidations).hasLength(eq(length), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(length);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).hasLength(eq(length), errorDescCaptor.capture()));
    }

    void testContains(Function<String, Validation<String>> methodInvoker) {
        String searchedValue = "a";
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getContainsMessage(searchedValue);
        doReturn(test.getExpectedValidation()).when(coreValidations).contains(eq(searchedValue), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(searchedValue);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).contains(eq(searchedValue), errorDescCaptor.capture()));
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
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Supplier<String[]>> validValueSupplierCaptor = ArgumentCaptor.forClass(Supplier.class);
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Supplier<ErrorDescription>> errorDescSupplierCaptor = ArgumentCaptor.forClass(Supplier.class);
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
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Supplier<Collection<String>>> validValueSupplierCaptor = ArgumentCaptor.forClass(Supplier.class);
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Supplier<ErrorDescription>> errorDescSupplierCaptor = ArgumentCaptor.forClass(Supplier.class);
        verify(coreValidations).isInList(validValueSupplierCaptor.capture(), errorDescSupplierCaptor.capture());
        test.assertErrorDesc(errorDescSupplierCaptor.getValue().get());
        assertThat(validValueSupplierCaptor.getValue().get()).isSameAs(validValues);
    }

    void testIsDate(Function<DateTimeFormatter, Validation<String>> methodInvoker) {
        doReturn(test.getExpectedMessage()).when(test.getMessageResolver()).getIsDateMessage(dateTimeFormatter);
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .isDate(same(dateTimeFormatter), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(dateTimeFormatter);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).isDate(same(dateTimeFormatter), errorDescCaptor.capture()));
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
        doReturn(test.getExpectedValidation()).when(coreValidations)
                .containsNotOnly(eq(searchedValue), any(ErrorDescription.class));
        Validation<String> validation = methodInvoker.apply(searchedValue);
        test.doAssertionsAndVerifications(validation,
                errorDescCaptor -> verify(coreValidations).containsNotOnly(eq(searchedValue), errorDescCaptor.capture()));
    }
}
