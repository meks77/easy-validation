package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static at.meks.validation.BehaviorDrivenTesting.given;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class VerificationTests<T, V extends AbstractVerifier<T, V>> {

    public static final String ARGUMENT_NAME = "expectedName";

    @Test
    final void givenValidValue() {
        given(getVerifierWithValidValue())
                .when(this::invokeVerificationMethod)
                .then(e -> assertThat(e).doesNotThrowAnyException());
    }

    protected abstract V getVerifierWithValidValue();

    protected abstract void invokeVerificationMethod(V v);

    @Test
    final void givenInvalidValue() {
        given(getVerifierWithInvalidValue())
                .when(this::invokeVerificationMethod)
                .then(e -> assertThat(e).hasMessage(null));
    }

    protected abstract V getVerifierWithInvalidValue();

    @Test
    final void givenInvalidValueArgumentNameAndEnglish() {
        given(getVerifierWithInvalidValue())
                .and(verifier -> verifier.withArgumentName(ARGUMENT_NAME))
                .and(this::localeIsEnglish)
                .when(this::invokeVerificationMethod)
                .then(e -> assertThat(e).hasMessage(getExpectedErrorMessage(ARGUMENT_NAME, Locale.ENGLISH)));
    }

    @Test
    final void givenInvalidValueArgumentNameAndGerman() {
        given(getVerifierWithInvalidValue())
                .and(verifier -> verifier.withArgumentName(ARGUMENT_NAME))
                .and(this::localeIsGerman)
                .when(this::invokeVerificationMethod)
                .then(e -> assertThat(e).hasMessage(getExpectedErrorMessage(ARGUMENT_NAME, Locale.GERMAN)));
    }

    @Test
    final void givenInvalidValueAndMessage() {
        String expectedMessage = "My expected Message";
        given(getVerifierWithInvalidValue())
                .and(verifier -> verifier.withArgumentName(ARGUMENT_NAME))
                .and(verifier -> verifier.withMessage(() -> expectedMessage))
                .when(this::invokeVerificationMethod)
                .then(e -> assertThat(e).hasMessage(expectedMessage));
    }

    protected abstract String getExpectedErrorMessage(String argumentName, Locale language);

    protected void localeIsEnglish() {
        Locale.setDefault(Locale.ENGLISH);
    }

    protected void localeIsGerman() {
        Locale.setDefault(Locale.GERMAN);
    }

}
