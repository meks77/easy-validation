package at.meks.validation.args;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS;

class ArchitectureTest {

    DescribedPredicate<JavaClass> publicMethodsReturnSelf = new DescribedPredicate<>("may only return itself") {
        @Override
        public boolean test(JavaClass javaClass) {
            return javaClass.isAssignableTo(AbstractVerifier.class)
                    && !javaClass.getFullName().equals(AbstractVerifier.class.getName());
        }
    };

    private final Pattern notAbstractVerifierPattern = Pattern.compile(".*(AbstractVerifier\\.class)$");

    private final ImportOption notAbstractVerifier = location -> !location.matches(notAbstractVerifierPattern);

    @Test
    void verifyVerifierPublicMethodsReturnImpl() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(DO_NOT_INCLUDE_TESTS)
                .withImportOption(notAbstractVerifier)
                .importPackages("at.meks.validation.args");

        ArchRuleDefinition.methods()
                .that().arePublic()
                .and().areDeclaredInClassesThat().areAssignableTo(AbstractVerifier.class)
                .and().areDeclaredInClassesThat().doNotBelongToAnyOf(BooleanVerifier.class)
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
