package at.meks.validation.complexobjectvalidation;

import at.meks.validation.Validation;
import at.meks.validation.complexobjectvalidation.model.Account;
import at.meks.validation.complexobjectvalidation.model.Bank;
import at.meks.validation.complexobjectvalidation.model.DeserializedPersonInfo;
import at.meks.validation.result.ValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static at.meks.validation.validations.date.DateValidationsWithErrorCode.isLocalDateTimeAfter;
import static at.meks.validation.validations.list.ListValidationsWithErrorCode.containsOnly;
import static at.meks.validation.validations.list.ListValidationsWithErrorCode.forType;
import static at.meks.validation.validations.list.ListValidationsWithErrorCode.hasMaxSize;
import static at.meks.validation.validations.list.ListValidationsWithErrorCode.hasMinSize;
import static at.meks.validation.validations.list.ListValidationsWithErrorCode.isNotEmpty;
import static at.meks.validation.validations.list.ListValidationsWithErrorCode.onProperty;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.containsNotOnly;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.hasLength;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.isDate;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.isInArray;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.isNotBlank;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.isNumeric;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.lengthIsBetween;
import static at.meks.validation.validations.string.StringValidationsWithErrorCode.lengthIsMoreThan;

/**
 * An example how a validator can be implemented for a complex object.
 */
class PersonInfoValidatorWithErrorCodes {

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

    PersonInfoValidatorWithErrorCodes() {
        setupValidationConfig();
    }

    private void setupValidationConfig() {
        nameValidation = isNotBlank("C0001").and(lengthIsMoreThan(1, "C0002"));
        postalCodeQuickValidation = isNotBlank("C0003").and(lengthIsBetween(4, 8, "C0004", "C0005"));
        postalCodeSlowValidation = isInArray(this::getValidPostalCodes, "C0006");
        birthDayStringValidation = isNotBlank("C0007").and(isDate(DEFAULT_DATE_FORMAT, "C0008"));
        birthDayDateValidation = isLocalDateTimeAfter(LocalDateTime.of(1940, 1, 1, 0, 0, 0), "C0009");
        accountNrStringValidation = isNotBlank("C0010").and(isNumeric("C0011")).and(containsNotOnly("0", "C0012"));
        accountSlowValidation = forType(Account.class, hasMinSize(1, "C0013")).and(hasMaxSize(5, "C0014"))
                .and(onProperty(Account::isActive, containsOnly(true, "C0015")))
                .and(onProperty(Account::isOnBadList, containsOnly(false, "C0016")));

        bankCodeValidation = isNotBlank("C0017").and(isNumeric("C0018")).and(hasLength(6, "C0019"));
        bankCodeSlowValidation = onProperty(Bank::isActive, isNotEmpty("C0020").and(containsOnly(true, "C0021")));
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
        Collections.addAll(accounts, Account.AccountBuilder.anAccount().withActive(true).build());
        return accounts;
    }
}
