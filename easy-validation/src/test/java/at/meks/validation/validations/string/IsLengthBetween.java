package at.meks.validation.validations.string;

import at.meks.validation.Validation;

@FunctionalInterface
public interface IsLengthBetween {

    Validation<String> isLengthBetween(int min, int max);

}
