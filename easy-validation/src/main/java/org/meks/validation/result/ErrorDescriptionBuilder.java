package org.meks.validation.result;

public class ErrorDescriptionBuilder {

    private ErrorDescriptionBuilder() {

    }

    public static ErrorDescription withCode(String message, String code) {
        return new ErrorDescriptionWithCode(message, code);
    }

    public static ErrorDescription withMessage(String message) {
        return new ErrorDescriptionWithMessage(message);
    }
}
