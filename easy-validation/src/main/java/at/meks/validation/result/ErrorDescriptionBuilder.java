package at.meks.validation.result;

public class ErrorDescriptionBuilder {

    private ErrorDescriptionBuilder() {

    }

    public static ErrorDescriptionWithCode withCode(String message, String code) {
        return new ErrorDescriptionWithCode(message, code);
    }

    public static ErrorDescription withMessage(String message) {
        return new ErrorDescriptionWithMessage(message);
    }
}
