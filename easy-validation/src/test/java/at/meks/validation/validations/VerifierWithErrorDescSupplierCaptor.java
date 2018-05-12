package at.meks.validation.validations;

import at.meks.validation.result.ErrorDescription;
import org.mockito.ArgumentCaptor;

import java.util.function.Supplier;

@FunctionalInterface
public interface VerifierWithErrorDescSupplierCaptor {

    void doVerification(ArgumentCaptor<Supplier<ErrorDescription>> errorDescCaptor);
}
