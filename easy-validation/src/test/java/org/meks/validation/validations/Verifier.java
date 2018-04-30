package org.meks.validation.validations;

import org.meks.validation.result.ErrorDescription;
import org.mockito.ArgumentCaptor;

@FunctionalInterface
public interface Verifier {

    void doVerification(ArgumentCaptor<ErrorDescription> errorDescCaptor);
}
