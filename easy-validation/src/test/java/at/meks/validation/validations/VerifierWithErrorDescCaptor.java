package at.meks.validation.validations;

import at.meks.validation.result.ErrorDescription;
import org.mockito.ArgumentCaptor;

@FunctionalInterface
public interface VerifierWithErrorDescCaptor {

    void doVerification(ArgumentCaptor<ErrorDescription> errorDescCaptor);
}
