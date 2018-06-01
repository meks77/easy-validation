package at.meks.validation;

import at.meks.validation.result.ValidationResult;
import org.mockito.ArgumentCaptor;

import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.any;

public class TestUtils {

    private TestUtils() {}

    public static void assertErrorResult(ValidationResult result, String expectedMessage) {
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrorMessage()).isEqualTo(expectedMessage);
    }

    public static void assertValidResult(ValidationResult result) {
        assertThat(result.isValid()).isTrue();
    }

    public static <T> void assertSupplierValue(T expectedValue, ArgumentCaptor<Supplier<T>> supplierCaptor) {
        assertThat(supplierCaptor.getValue().get()).isEqualTo(expectedValue);
    }

    public static <T> ArgumentCaptor<Supplier<T>> getSupplierCaptor() {
        //noinspection unchecked
        return ArgumentCaptor.forClass(Supplier.class);
    }

    public static <T> Supplier<T> anySupplier() {
        //noinspection unchecked
        return any(Supplier.class);
    }
}
