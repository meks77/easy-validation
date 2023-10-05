package at.meks.validation.args;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS;

class ArchitectureTest {

    /**
     * TODO AbstractVerifier public Methods, except matches, must return extension of AbstractVerifier
     */

    DescribedPredicate<JavaClass> publicMethodsReturnSelf = new DescribedPredicate<JavaClass>("may only return itself") {
        @Override
        public boolean apply(JavaClass javaClass) {
            return javaClass.getMethods().stream()
                    .filter(javaMethod -> javaMethod.getModifiers().contains(JavaModifier.PUBLIC))
                    .allMatch(javaMethod -> javaMethod.getRawReturnType().isAssignableTo(AbstractVerifier.class)
                            &&!javaMethod.getRawReturnType().getFullName().equals(AbstractVerifier.class.getName())
                    );
        }
    };

    private final Pattern notAbstractVerifierPattern = Pattern.compile(".*(AbstractVerifier\\.class)$");

    private final ImportOption notAbstractVerifier = location -> !location.matches(notAbstractVerifierPattern);

    @Test
    void verifyVerifierPublicMethodsReturnImpl() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(DO_NOT_INCLUDE_TESTS).withImportOption(notAbstractVerifier)
                .importPackages("at.meks.validation.args")
                .that(DescribedPredicate.doNot(DescribedPredicate.equalTo(AbstractVerifier.class)));

        ArchRuleDefinition.methods()
                .that().arePublic()
                .and().areDeclaredInClassesThat().areAssignableTo(AbstractVerifier.class)
                .should().haveRawReturnType(publicMethodsReturnSelf)
                .check(importedClasses);
    }

    @Test
    void verfyVerifierClassesArePublic() {
        JavaClasses importedClasses = new ClassFileImporter().withImportOption(DO_NOT_INCLUDE_TESTS)
                .importPackages("at.meks.validation.args");

        ArchRuleDefinition.classes()
                .that().areAssignableTo(AbstractVerifier.class)
                .should().bePublic()
                .check(importedClasses);
    }

    @Test
    void verifyVerifierConstructorsArePackagePrivate() {
        JavaClasses importedClasses = new ClassFileImporter().withImportOption(DO_NOT_INCLUDE_TESTS)
                .importPackages("at.meks.validation.args");

        ArchRuleDefinition.constructors()
                .that().areDeclaredInClassesThat().areAssignableTo(AbstractVerifier.class)
                .should().bePackagePrivate()
                .orShould().beProtected()
                .check(importedClasses);
    }
}
