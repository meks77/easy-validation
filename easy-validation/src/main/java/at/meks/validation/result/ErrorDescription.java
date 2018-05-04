package at.meks.validation.result;

import java.io.Serializable;

public class ErrorDescription implements Serializable {

    private String message;
    private String errorCode;

    ErrorDescription() {

    }

    public String getErrorMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
