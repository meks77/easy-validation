# easy-validation matchers

## Goal
The goal of the matchers was providing simple methods which return if a value matches an expectation.

As mentioned, the first idea was to use commons-lang3 instead, but this can lead to dependency problems in projects 
using the easy-validation. Furthermore, the artefact size is huge. Just for validating some simple arguments, 
you don't want to import such a dependency.

Therefore I created methods similar to those methods in commons-lang3.
I just added methods which came in my mind, which can be used often.

If you are missing a validation, you can either provide your own matchers. But you are also welcome to extend the
easy-validation-matchers.

The matchers are currently used in the module easy-validation-args. There you can see how they are used in the validation.

## Maven Coordinates

```xml
<dependency>
    <groupId>at.meks.easyvalidation</groupId>
    <artifactId>matcher</artifactId>
    <version>2.0.0</version>
</dependency>
```