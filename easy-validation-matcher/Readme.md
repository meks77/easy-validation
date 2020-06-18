# easy-validation matchers

## Goal
The goal of the matchers was provide simple methods which return if a value matches an expectation.

As mentioned, the first idea was to use commons-lang3 instead, but this can lead to dependency probleme at projects 
using the easy-validation. Furthermore the artefact size is realy big. And just for validating some simple arguments, 
you don't want to import such a dependency.

Therefor I created methods similar to those methods in commons-lang3.
I just added methods which came in my mind, which can be used often.

If you are missing a validation, you can either provide your own matchers. But you are also welcome to extend the
easy-validation-matchers.

The matchers are currently used in the module easy-validation-args. There you can see how they are used in the validation.