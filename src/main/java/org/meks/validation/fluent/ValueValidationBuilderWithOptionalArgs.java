package org.meks.validation.fluent;

public class ValueValidationBuilderWithOptionalArgs<T> {

    private final ValueValidation<T> validation;

    ValueValidationBuilderWithOptionalArgs(ValueValidation<T> validation) {
        this.validation = validation;
    }

    public ValueValidationBuilderWithOptionalArgs<T> withValueDesc(String valueDesc) {
        validation.setValueDescrption(valueDesc);
        return this;
    }

    public ValueValidation<T> getValidation() {
        return validation;
    }
}
