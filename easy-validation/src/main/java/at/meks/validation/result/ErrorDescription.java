package at.meks.validation.result;

import java.io.Serializable;

/**
 * This class contains the error message and optional an error code, in the case the validation failed.
 */
public class ErrorDescription implements Serializable {

    private String message;
    private String errorCode;

    ErrorDescription() {

    }

    /**
     * @return the error message why the validation failed
     */
    public String getErrorMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return error code of the validation, which was optionally set when creating the validation
     */
    public String getErrorCode() {
        return errorCode;
    }

    void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
