[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=bugs)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=coverage)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=at.meks.easyvalidation%3Aparent&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=at.meks.easyvalidation%3Aparent)
# easy-validation 

Very easy way to validate simple and complex objects by using a fluent interface.

## Goals

 * No 3rd party dependencies
 * Tiny artifact size
 * clean and natural api

## Examples

### Simply validate a method argument and throw an IllegalArgumentException

If you want to validate just a method argument you can do this by using the module easy-validation-args.

````
validate().that(arg1)
        .withMessage(() -> "arg1")
        .isNotBlank()
        .hasMinLength(30)
        .matches(value -> value.contains("whatIsExpected"));
````

Commonly when you implement a validation the code would look like this:

````
if (StringUtils.isBlank(arg1)) {
    throw new IllegalArgumentException("arg1");
}
if (arg1.length() <= 30) {
    throw new IllegalArgumentException("arg1");
}
if (arg1.contains("whatIsExpected") {
    throw new IllegalArgumentException("arg1);
}
````
Just imagine you have 20+ different input values you have to validate. The code is growing very fast, and it will get hard
to maintain as it grows.

You also have the possibility to set a seperate message per validation.
````
validate().that(arg1)
        .withMessage(() -> "arg1 mustn't be blank").isNotBlank()
        .withMessage(() -> "arg1 must have at leat 30 chars").hasMinLength(30)
        .withMessage(() -> "arg1 must contain \"whatIsExpected\"").matches(value -> value.contains("whatIsExpected"));
````

### Do more validations to report in any way you want

Expect the case you want to validate the input to be not blank, and it mustn't contain whitespace, but you want to report
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

Therefore, I decided to use my own matchers. Currently in version 2.0.0-RC2 the size is ~ 6 KB. 
Furthermore the whole validation has no dependency to any 3rd party library. So you can use always as long you use at least
java 11.  

## Details and Maven Coordinates
More Details and Maven Coordinates can be found at the modules:

* [Matchers](matcher/Readme.md)
* [Core](core/Readme.md)
* [Args](args/Readme.md)

## History 
While developing the first release I followed the main concept of using lambda combined with a fluent interface is from Joel Planes, what can be found [here](https://medium.com/@jplanes/lambda-validations-with-java-8-86aa8143bd9f).

But I was getting tired to always invoke the method test and afterward the method throwIfInvalid.

Thanks to Eric Evans' input, I developed it from scratch with the possibility to decide if I want to throw an exception or not easy, without duplicating so much method signatures.
I hope you like the result.
