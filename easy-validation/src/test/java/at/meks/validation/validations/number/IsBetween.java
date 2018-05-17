package at.meks.validation.validations.number;

import at.meks.validation.Validation;

@FunctionalInterface
public interface IsBetween {

    Validation<Comparable<Number>> isBetween(Number min, Number max);
}
