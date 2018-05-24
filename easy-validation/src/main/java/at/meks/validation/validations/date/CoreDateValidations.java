package at.meks.validation.validations.date;

import at.meks.validation.validations.common.CommonValidations;

/**
 * is*After, is*Before and isBetween are just delegated to {@link CommonValidations} to ease the use of of those
 * methods with the LocalDate, LocalDateTime or ZoneDateTime. Sadly, if simply overloaded isAfter with the different
 * types leads to a compilation error because of the same signature of the methods.
 */
class CoreDateValidations {



}
