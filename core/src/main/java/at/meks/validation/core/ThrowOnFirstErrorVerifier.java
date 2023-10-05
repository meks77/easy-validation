package at.meks.validation.core;

import java.util.function.Supplier;

public class ThrowOnFirstErrorVerifier<T> {

    private T verifiedValue;
    private Supplier<? extends Exception> throwableSupplier;

    public ThrowOnFirstErrorVerifier(T verifiedValue, Supplier<? extends Exception> throwableSupplier) {
        this.verifiedValue = verifiedValue;
        this.throwableSupplier = throwableSupplier;
    }

    public ThrowOnFirstErrorVerifier<T> matches(Matcher<T> matcher) {
        if (!matcher.verify(verifiedValue)) {
            throw new NotMatchingException(throwableSupplier.get());
        }
        return this;
    }

    public ThrowOnFirstErrorVerifier<T> and(Matcher<T> machter) {
        return matches(machter);
    }

}
