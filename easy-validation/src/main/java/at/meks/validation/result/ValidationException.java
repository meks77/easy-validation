package at.meks.validation.result;

/**
 * This class is used as exception if a validatino fails. It contains a description what was validated and the
 * {@link ErrorDescription}.
 */
public class ValidationException extends Exception {

    private final String valueDescription;
    private final ErrorDescription errorDescription;

    ValidationException(String valueDescription, ErrorDescription errorDescription) {
        this.valueDescription = valueDescription;
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        if (getErrorCode() == null) {
            return String.format("%s: %s", valueDescription, errorDescription.getErrorMessage());
        } else {
            return String.format("%s: %s - %s", valueDescription, getErrorCode(), errorDescription.getErrorMessage());
        }
    }

    /**
     * @return error code which was set when the validaton was created
     */
    @SuppressWarnings("WeakerAccess")
    public String getErrorCode() {
        return errorDescription.getErrorCode();
    }
}
