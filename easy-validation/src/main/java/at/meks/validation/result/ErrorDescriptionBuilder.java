package at.meks.validation.result;

public class ErrorDescriptionBuilder {

    private ErrorDescriptionBuilder() {

    }

    public static ErrorDescription withMessage(String message) {
        return withCode(message, null);
    }

    public static ErrorDescription withCode(String message, String code) {
        ErrorDescription description = new ErrorDescription();
        description.setMessage(message);
        description.setErrorCode(code);
        return description;
    }
}
