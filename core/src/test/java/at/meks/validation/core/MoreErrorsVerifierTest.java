package at.meks.validation.core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MoreErrorsVerifierTest {

    @Test
    void given2MatcherOneViolates() {
        ValidationErrorListener errorListener = Mockito.mock(ValidationErrorListener.class);
        MoreErrorsVerifier<String> verifier = new MoreErrorsVerifier<>("test", errorListener);
        verifier.usingKey("first").matches(value -> true)
                .and().usingKey("second").matches(value -> false);

        Mockito.verify(errorListener).onValidationError("second");

    }

}