package at.meks.validation.args.errormessage;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ResourceBundleErrorMessage implements ErrorMessage {

    private String messageKey;
    private Object[] messageArgs;

    public ResourceBundleErrorMessage(String messageKey, Object...messageArgs) {
        this.messageKey = messageKey;
        this.messageArgs = messageArgs;
    }

    @Override
    public String asText() {
        ResourceBundle bundle = ResourceBundle.getBundle("at.meks.validation.args.errormessage.errorMessages");
        return MessageFormat.format(bundle.getString(messageKey), messageArgs);
    }
}
