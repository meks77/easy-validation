package at.meks.validation.result;

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

    @SuppressWarnings("WeakerAccess")
    public String getErrorCode() {
        return errorDescription.getErrorCode();
    }
}
