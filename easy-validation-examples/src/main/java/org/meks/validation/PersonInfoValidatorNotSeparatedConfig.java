package org.meks.validation;

import org.meks.validation.result.ValidationException;
import org.meks.validation.validations.list.ListValidations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.meks.validation.Account.AccountBuilder.anAccount;
import static org.meks.validation.validations.date.DateValidations.isDateAfter;
import static org.meks.validation.validations.list.ListValidations.containsOnly;
import static org.meks.validation.validations.list.ListValidations.forType;
import static org.meks.validation.validations.list.ListValidations.hasMaxSize;
import static org.meks.validation.validations.list.ListValidations.hasMinSize;
import static org.meks.validation.validations.list.ListValidations.onProperty;
import static org.meks.validation.validations.string.StringValidations.containsNotOnly;
import static org.meks.validation.validations.string.StringValidations.hasLength;
import static org.meks.validation.validations.string.StringValidations.isDate;
import static org.meks.validation.validations.string.StringValidations.isInArray;
import static org.meks.validation.validations.string.StringValidations.isNotBlank;
import static org.meks.validation.validations.string.StringValidations.isNumeric;
import static org.meks.validation.validations.string.StringValidations.lengthIsBetween;
import static org.meks.validation.validations.string.StringValidations.lengthIsMoreThan;

/**
 * An example how a validator can be implemented for a complex object.
 */
class PersonInfoValidatorNotSeparatedConfig {

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.BASIC_ISO_DATE;

    private String[] getValidPostalCodes() {
        return new String[]{"1010", "1020", "1030", "1040", "1050"};
    }

    void validatePerson(DeserializedPersonInfo personInfo) throws ValidationException {
        isNotBlank().and(lengthIsMoreThan(1)).test(personInfo.getFirstName()).throwIfInvalid("firstName");
        isNotBlank().and(lengthIsMoreThan(1)).test(personInfo.getName()).throwIfInvalid("name");
        isNotBlank().and(lengthIsBetween(4, 8)).and(isInArray(this::getValidPostalCodes))
                .test(personInfo.getPostalCode()).throwIfInvalid("postalCode");

        isNotBlank().and(isDate(DEFAULT_DATE_FORMAT)).test(personInfo.getBirthDate()).throwIfInvalid("birthDay");
        LocalDateTime birthDayDate = LocalDateTime.from(DEFAULT_DATE_FORMAT.parse(personInfo.getBirthDate()));
        isDateAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0)).test(birthDayDate).throwIfInvalid("birthDay");

        isNotBlank().and(isNumeric()).and(containsNotOnly("0")).test(personInfo.getAccount()).throwIfInvalid("account");
        forType(Account.class, hasMinSize(1)).and(hasMaxSize(5))
                .and(onProperty(Account::isActive, containsOnly(true)))
                .and(onProperty(Account::isOnBadList, containsOnly(false)))
                .test(getAccountsOfPersonOfSlowService()).throwIfInvalid("account");

        isNotBlank().and(isNumeric()).and(hasLength(6)).test(personInfo.getBankCode());
        onProperty(Bank::isActive, ListValidations.isNotEmpty().and(containsOnly(true)))
                .test(getBankCodeOfPersonOfSlowService()).throwIfInvalid("bankCode");
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
