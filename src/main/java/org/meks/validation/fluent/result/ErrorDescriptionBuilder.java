package org.meks.validation.fluent.result;

public class ErrorDescriptionBuilder {


    public static ErrorDescription withCode(String message, String code) {
        return new ErrorDescriptionWithCode(message, code);
    }

    public static ErrorDescription withMessage(String message) {
        return new ErrorDescriptionWithMessage(message);
    }
}
