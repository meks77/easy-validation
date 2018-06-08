package at.meks.validation.customvalidations;

import at.meks.validation.result.ValidationException;

import static at.meks.validation.customvalidations.PersonValidations.isPoliceOfficer;
import static at.meks.validation.customvalidations.PersonValidations.monthsInProfessionIsMoreThan;
import static at.meks.validation.validations.common.CommonValidations.isEqualTo;
import static at.meks.validation.validations.common.CommonValidations.isGreaterThan;

public class MyPersonService {

    public void validateForTrusablePoliceOfficer(Person person) throws ValidationException {
        //using the custom validation
        isPoliceOfficer().and(monthsInProfessionIsMoreThan(20)).test(person).throwIfInvalid("person");

        // same validation but using the built in validation methods
        isEqualTo("police officer").test(person.getProfession()).throwIfInvalid("profession");
        isGreaterThan(20).test(person.getActiveMonthsInProfession()).throwIfInvalid("actice months in profession");
    }
}
