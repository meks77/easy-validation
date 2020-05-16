package at.meks.validation.core;

import java.util.function.Supplier;

public class ThrowOnFirstErrorValidator {

    private Supplier<? extends Exception> throwableSupplier;

    ThrowOnFirstErrorValidator() {

    }

    public ThrowOnFirstErrorValidator throwing(Supplier<? extends Exception> throwableSupplier) {
        this.throwableSupplier = throwableSupplier;
        return this;
    }

    public <T> ThrowOnFirstErrorVerifier<T> verify(T validatedValue) {
        return new ThrowOnFirstErrorVerifier<>(validatedValue, throwableSupplier);
    }
}
