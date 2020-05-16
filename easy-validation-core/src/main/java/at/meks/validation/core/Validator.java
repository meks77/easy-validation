package at.meks.validation.core;

import java.util.function.Consumer;

public class Validator {

    private Validator() {

    }

    public static ThrowOnFirstErrorValidator stopOnFirstError() {
        return new ThrowOnFirstErrorValidator();
    }

    public static MoreErrorsValidator reportTo(ValidationErrorListener errorListener) {
        return new MoreErrorsValidator(errorListener);
    }

}
