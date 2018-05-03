package at.meks.validation;

import at.meks.validation.result.ValidationException;
import at.meks.validation.validations.list.ListValidations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static at.meks.validation.Account.AccountBuilder.anAccount;
import static at.meks.validation.validations.date.DateValidations.isDateAfter;
import static at.meks.validation.validations.list.ListValidations.containsOnly;
import static at.meks.validation.validations.list.ListValidations.forType;
import static at.meks.validation.validations.list.ListValidations.hasMaxSize;
import static at.meks.validation.validations.list.ListValidations.hasMinSize;
import static at.meks.validation.validations.list.ListValidations.onProperty;
import static at.meks.validation.validations.string.StringValidations.containsNotOnly;
import static at.meks.validation.validations.string.StringValidations.hasLength;
import static at.meks.validation.validations.string.StringValidations.isDate;
import static at.meks.validation.validations.string.StringValidations.isInArray;
import static at.meks.validation.validations.string.StringValidations.isNotBlank;
import static at.meks.validation.validations.string.StringValidations.isNumeric;
import static at.meks.validation.validations.string.StringValidations.lengthIsBetween;
import static at.meks.validation.validations.string.StringValidations.lengthIsMoreThan;

/**
 * An example how a validator can be implemented for a complex object.
 */
class PersonInfoValidator {

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.BASIC_ISO_DATE;

    private Validation<String> nameValidation;
    private Validation<String> postalCodeQuickValidation;
    private Validation<String> postalCodeSlowValidation;
    private Validation<String> birthDayStringValidation;
    private Validation<LocalDateTime> birthDayDateValidation;
    private Validation<String> accountNrStringValidation;
    private Validation<List<Account>> accountSlowValidation;
    private Validation<String> bankCodeValidation;
    private Validation<List<Bank>> bankCodeSlowValidation;

    PersonInfoValidator() {
        setupValidationConfig();
    }

    private void setupValidationConfig() {
        nameValidation = isNotBlank().and(lengthIsMoreThan(1));
        postalCodeQuickValidation = isNotBlank().and(lengthIsBetween(4, 8));
        postalCodeSlowValidation = isInArray(this::getValidPostalCodes);
        birthDayStringValidation = isNotBlank().and(isDate(DEFAULT_DATE_FORMAT));
        birthDayDateValidation = isDateAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0));
        accountNrStringValidation = isNotBlank().and(isNumeric()).and(containsNotOnly("0"));
        accountSlowValidation = forType(Account.class, hasMinSize(1)).and(hasMaxSize(5))
                .and(onProperty(Account::isActive, containsOnly(true)))
                .and(onProperty(Account::isOnBadList, containsOnly(false)));

        bankCodeValidation = isNotBlank().and(isNumeric()).and(hasLength(6));
        bankCodeSlowValidation = onProperty(Bank::isActive, ListValidations.isNotEmpty().and(containsOnly(true)));
    }

    private String[] getValidPostalCodes() {
        return new String[]{"1010", "1020", "1030", "1040", "1050"};
    }

    void validatePerson(DeserializedPersonInfo personInfo) throws ValidationException {
        nameValidation.test(personInfo.getFirstName()).throwIfInvalid("firstName");
        nameValidation.test(personInfo.getName()).throwIfInvalid("name");
        postalCodeQuickValidation.test(personInfo.getPostalCode()).throwIfInvalid("postalCode");
        birthDayStringValidation.test(personInfo.getBirthDate()).throwIfInvalid("birthDay");
        LocalDateTime birthDayDate = LocalDateTime.from(DEFAULT_DATE_FORMAT.parse(personInfo.getBirthDate()));
        birthDayDateValidation.test(birthDayDate).throwIfInvalid("birthDay");
        accountNrStringValidation.test(personInfo.getAccount()).throwIfInvalid("account");
        bankCodeValidation.test(personInfo.getBankCode());

        postalCodeSlowValidation.test(personInfo.getPostalCode()).throwIfInvalid("postalCode");
        accountSlowValidation.test(getAccountsOfPersonOfSlowService()).throwIfInvalid("account");
        bankCodeSlowValidation.test(getBankCodeOfPersonOfSlowService()).throwIfInvalid("bankCode");
    }

    private List<Bank> getBankCodeOfPersonOfSlowService() {
        return Collections.emptyList();
    }

    private List<Account> getAccountsOfPersonOfSlowService() {
        ArrayList<Account> accounts = new ArrayList<>();
        Collections.addAll(accounts, anAccount().withActive(true).build());
        return accounts;
    }
}
