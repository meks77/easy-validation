package org.meks.validation;

import org.meks.validation.result.ValidationException;
import org.meks.validation.list.ListValidations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.meks.validation.Account.AccountBuilder.anAccount;
import static org.meks.validation.DateValidations.isDateAfter;
import static org.meks.validation.StringValidations.hasLength;
import static org.meks.validation.StringValidationsWithErrorCode.isDate;
import static org.meks.validation.StringValidationsWithErrorCode.isInArray;
import static org.meks.validation.StringValidationsWithErrorCode.isNotBlank;
import static org.meks.validation.StringValidationsWithErrorCode.isNumeric;
import static org.meks.validation.StringValidations.lengthIsBetween;
import static org.meks.validation.StringValidations.lengthIsMoreThan;

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
        nameValidation = isNotBlank("1").and(lengthIsMoreThan(1));
        postalCodeQuickValidation = isNotBlank("2").and(lengthIsBetween(4, 8));
        postalCodeSlowValidation = isInArray(this::getValidPostalCodes, "3");
        birthDayStringValidation = isNotBlank("4").and(isDate(DEFAULT_DATE_FORMAT, "4"));
        birthDayDateValidation = isDateAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0), "5");
        accountNrStringValidation = isNotBlank("6").and(isNumeric("6")).and(StringValidations.containsNotOnly("0"));
        accountSlowValidation = ListValidations.forType(Account.class, ListValidations.hasMinSize(1)).and(ListValidations.hasMaxSize(5))
                .and(ListValidations.onProperty(Account::isActive, ListValidations.containsOnly(true)))
                .and(ListValidations.onProperty(Account::isOnBadList, ListValidations.containsOnly(false)));

        bankCodeValidation = isNotBlank("7").and(isNumeric("8")).and(hasLength(6));
        bankCodeSlowValidation = ListValidations.onProperty(Bank::isActive, ListValidations.isNotEmpty().and
                (ListValidations.containsOnly(true)));
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
