package org.meks.validation.fluent;

import org.meks.validation.fluent.result.ValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.meks.validation.fluent.Account.AccountBuilder.anAccount;
import static org.meks.validation.fluent.DateValidations.isDateAfter;
import static org.meks.validation.fluent.StringValidations.hasLength;
import static org.meks.validation.fluent.StringValidations.isDate;
import static org.meks.validation.fluent.StringValidations.isInList;
import static org.meks.validation.fluent.StringValidations.isNotBlank;
import static org.meks.validation.fluent.StringValidations.isNumeric;
import static org.meks.validation.fluent.StringValidations.lengthIsBetween;
import static org.meks.validation.fluent.StringValidations.lengthIsMoreThan;
import static org.meks.validation.fluent.list.ListValidations.containsOnly;
import static org.meks.validation.fluent.list.ListValidations.forType;
import static org.meks.validation.fluent.list.ListValidations.hasMaxSize;
import static org.meks.validation.fluent.list.ListValidations.hasMinSize;
import static org.meks.validation.fluent.list.ListValidations.isNotEmpty;
import static org.meks.validation.fluent.list.ListValidations.onProperty;

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
        postalCodeSlowValidation = isInList(this::getValidPostalCodes, "3");
        birthDayStringValidation = isNotBlank("4").and(isDate(DEFAULT_DATE_FORMAT, "4"));
        birthDayDateValidation = isDateAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0), "5");
        accountNrStringValidation = isNotBlank("6").and(isNumeric("6")).and(StringValidations.containsNotOlny("0"));
        accountSlowValidation = forType(Account.class, hasMinSize(1)).and(hasMaxSize(5))
                .and(onProperty(Account::isActive, containsOnly(true)))
                .and(onProperty(Account::isOnBadList, containsOnly(false)));

        bankCodeValidation = isNotBlank("7").and(isNumeric("8")).and(hasLength(6));
        bankCodeSlowValidation = onProperty(Bank::isActive, isNotEmpty().and(containsOnly(true)));
    }

    private String[] getValidPostalCodes() {
        return new String[]{"1010", "1020", "1030", "1040", "1050"};
    }

    void validatePerson(DeserializedPersonInfo personInfo) throws ValidationException {
        nameValidation.test(personInfo::getFirstName).throwIfInvalid("firstName");
        nameValidation.test(personInfo::getName).throwIfInvalid("name");
        postalCodeQuickValidation.test(personInfo::getPostalCode).throwIfInvalid("postalCode");
        birthDayStringValidation.test(personInfo::getBirthDate).throwIfInvalid("birthDay");
        LocalDateTime birthDayDate = LocalDateTime.from(DEFAULT_DATE_FORMAT.parse(personInfo.getBirthDate()));
        birthDayDateValidation.test(() -> birthDayDate).throwIfInvalid("birthDay");
        accountNrStringValidation.test(personInfo::getAccount).throwIfInvalid("account");
        bankCodeValidation.test(personInfo::getBankCode);

        postalCodeSlowValidation.test(personInfo::getPostalCode).throwIfInvalid("postalCode");
        accountSlowValidation.test(this::getAccountsOfPersonOfSlowService).throwIfInvalid("account");
        bankCodeSlowValidation.test(this::getBankCodeOfPersonOfSlowService).throwIfInvalid("bankcode");
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
