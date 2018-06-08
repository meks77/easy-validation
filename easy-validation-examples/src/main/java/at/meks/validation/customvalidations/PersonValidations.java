package at.meks.validation.customvalidations;

import at.meks.validation.Validation;

import static at.meks.validation.SimpleValidation.from;
import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;
import static java.lang.String.format;

class PersonValidations {

    private PersonValidations() {}

    static Validation<Person> isPoliceOfficer() {
        return from(person -> person.getProfession().equals("police officer"),
                () -> withMessage("must be a police officer"));
    }

    static Validation<Person> monthsInProfessionIsMoreThan(int minimumMonths) {
        return from(person -> person.getActiveMonthsInProfession() > minimumMonths,
                () -> withMessage(format("must be active in his profession for at least %s months", minimumMonths)));
    }

}
