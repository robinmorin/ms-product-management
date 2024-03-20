package org.test.capitole.unittests.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.apache.logging.log4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

/**
 * Checking Coding Rules Architecture Test (Rules Clean Code)
 */
@AnalyzeClasses(packages = "org.test.capitole")
public class CodingArchitectureTest {

    @ArchTest
    private final ArchRule checkClassesUseLoggerStaticFinal =
        fields().that().haveRawType(Logger.class)
                       .should().bePrivate()
                       .andShould().beStatic()
                       .andShould().beFinal()
                       .allowEmptyShould(true)
                       .because("All classes should use Logger as static final");

    @ArchTest
    private final ArchRule checkClassesNotUseFieldInjection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

}
