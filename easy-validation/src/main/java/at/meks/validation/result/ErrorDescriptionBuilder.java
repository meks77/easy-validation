package at.meks.validation.result;

/**
 * The builder for the {@link ErrorDescription}.
 */
public class ErrorDescriptionBuilder {

    private ErrorDescriptionBuilder() {

    }

    /**
     * creates a {@link ErrorDescription} where only the message is set.
     * @param message   is used for the description
     * @return  new instance {@link ErrorDescription}
     */
    public static ErrorDescription withMessage(String message) {
        return withCode(message, null);
    }

    /**
     * creates a {@link ErrorDescription} where the message and the code are set.
     * @param message   is set to the description
     * @param code  is set to the description
     * @return  new instance with set values
     */
    public static ErrorDescription withCode(String message, String code) {
        ErrorDescription description = new ErrorDescription();
        description.setMessage(message);
        description.setErrorCode(code);
        return description;
    }
}
