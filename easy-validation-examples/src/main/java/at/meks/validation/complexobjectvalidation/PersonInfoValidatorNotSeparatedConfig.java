package at.meks.validation.complexobjectvalidation;

import at.meks.validation.complexobjectvalidation.model.Account;
import at.meks.validation.complexobjectvalidation.model.Bank;
import at.meks.validation.complexobjectvalidation.model.DeserializedPersonInfo;
import at.meks.validation.result.ValidationException;
import at.meks.validation.validations.list.ListValidations;
import at.meks.validation.validations.string.StringValidations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static at.meks.validation.validations.date.DateValidations.isLocalDateTimeAfter;
import static at.meks.validation.validations.list.ListValidations.containsOnly;
import static at.meks.validation.validations.list.ListValidations.forType;
import static at.meks.validation.validations.list.ListValidations.hasMaxSize;
import static at.meks.validation.validations.list.ListValidations.isNotEmpty;
import static at.meks.validation.validations.list.ListValidations.onProperty;
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

        isNotBlank().and(isDate(DEFAULT_DATE_FORMAT))
                .and(this::parseToDate, isLocalDateTimeAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0)))
                .test(personInfo.getBirthDate()).throwIfInvalid("birthDay");

        isNotBlank().and(isNumeric()).and(StringValidations.containsNotOnly("0")).test(personInfo.getAccount())
                .throwIfInvalid("account");
        forType(Account.class, ListValidations.hasMinSize(1)).and(hasMaxSize(5))
                .and(onProperty(Account::isActive, containsOnly(true)))
                .and(onProperty(Account::isOnBadList, containsOnly(false)))
                .test(getAccountsOfPersonOfSlowService()).throwIfInvalid("account");

        isNotBlank().and(isNumeric()).and(hasLength(6)).test(personInfo.getBankCode());
        onProperty(Bank::isActive, isNotEmpty().and(containsOnly(true))).test(getBankCodeOfPersonOfSlowService())
                .throwIfInvalid("bankCode");
    }

    private LocalDateTime parseToDate(String s) {
        return LocalDateTime.from(DEFAULT_DATE_FORMAT.parse(s));
    }

    private List<Bank> getBankCodeOfPersonOfSlowService() {
        return Collections.emptyList();
    }

    private List<Account> getAccountsOfPersonOfSlowService() {
        ArrayList<Account> accounts = new ArrayList<>();
        Collections.addAll(accounts, Account.AccountBuilder.anAccount().withActive(true).build());
        return accounts;
    }
}
