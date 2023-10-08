# Easy Validation Args
## Motivation
I'd like to validate method arguments with a simple API like

```java
import java.util.Objects;

public class MyClass {

    public void myMethod(String arg1) {
        Objects.requireNonNull(arg1);
    }

}
```

But often a little bit more than just not null must be verified.

The first release of the easy validations wasn't bad, but complicated to use for this case.

Therfor the new API was created.

## How to use it

```java
import static at.meks.validation.args.ArgValidator.validate;

public class MyClass {

    public void myMethod(String arg1) {
        validate().that(arg1)
            .withMessage(() -> "Error message for arg1")
            .isNotBlank()
            .hasMinLength(30)
            .matches(value -> value.contains("whatIsExpected"));
    }

}
```

## Features
* fluent API usage
* always throws IllegalArgumentException
* extendable by custom validations by using the method "matches"
* no dependencies to 3rd party libs
* very small artifact size

## Maven Coordinates
```xml
<dependency>
    <groupId>at.meks.easyvalidation</groupId>
    <artifactId>args</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Outlook
Currently there just a hand full verifier like for

* Booleans
* Comparables
* String and
* Collections

I plan to add verifier as soon as I need it. Contributors are very welcome.


