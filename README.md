[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=bugs)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=code_smells)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=coverage)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=sqale_index)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=security_rating)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=at.meks%3Aeasy-validation-parent&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=at.meks%3Aeasy-validation-parent)
# easy-validation 

Very easy way to validate simple and complex objects by using an fluent interface.

## Goals

 * No 3rd party dependencies
 * Very small artefact size
 * clean and natural api

## Exmples

### Simply validate a method argument and throw an IllegalArgumentException

If you want to validate just a method argument you can do this by using the module easy-validation-args.

````
validate().that(arg1)
        .isNotBlank()
        .hasMinLength(30)
        .matches(value -> value.contains("whatIsExpected"));
````

Commonly when you implement a validation the code would look like this:

````
if (StringUtils.isBlank(arg1)) {
    throw new IllegalArgumentException();
}
if (arg1.length() <= 30) {
    throw new IllegalArgumentException();
}
if (arg1.contains("whatIsExpected") {
    throw new IllegalArgumentException();
}
````
Just image you have 20+ different input values you have to validate. The code is growing very fast and it will get hard
to maintain is it grows.

### Do more validations to report in any way you want

Expect the case you want to validate the input to be not blank and it mustn't contain whitespace, but you want to report
it with different error messages.

````
List<String> occuredErrorKeys = new ArrayList<>();
String validatedValue = " ";
Validator.reportTo(occuredErrorKeys::add)
        .verify(validatedValue)
        .usingKey("notBlank").matches(StringUtils::isNotBlank)
        .and().usingKey("whitespaces").matches(StringUtils::doesNotContainWhitespace)
        .and().usingKey("minLength").matches(value -> StringUtils.length(value) > 10);
````
In the list occuredErrorKeys the errors notBlank, whitespaces and minLength will be added. With this information you can report
the error however you want.

### Throw the Exception you want
If you have the need to throw your own exception in case of the first violation, e.g. for throwing an error code, just
go ahead like this:
````
Validator.stopOnFirstError()
        .throwing(() -> new MyCheckedExceptionWithErrorCode(999))
        .verify(validatedValue)
        .matches(StringUtils::isNotBlank)
        .and(StringUtils::doesNotContainWhitespace)
````

## Matchers
First I thought using commons-lang3 is fine. But I realized that commons-lang3 size is about 500 KB.
Furthermore: How to avoid dependency clashes?

Therefore I decided to use my own matchers. Currently in version 2.0.0-RC2 the size is ~ 6 KB. 
Furthermore the whole validation has no dependency to any 3rd party library. So you can use allways as long you use at least
java 8.  

## History 
While developing the first release I followed the main concept of using lambda combined with a fluent interface is from Joel Planes, what can be found [here](https://medium.com/@jplanes/lambda-validations-with-java-8-86aa8143bd9f).

But I was getting tired to always invoke the method test and afterwards the method throwIfInvalid.

Thanks to Eric Evans input I developed it from scratch with the possibility to decide if I want to throw an exception or not easy, without duplicating so much method signatures.
I hope you like the result.
