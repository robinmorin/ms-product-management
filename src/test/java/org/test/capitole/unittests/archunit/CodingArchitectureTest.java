package org.test.capitole.unittests.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

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
    private final ArchRule checkClassesNotUseFieldInjection =
            ArchRuleDefinition.noClasses()
                    .that().resideOutsideOfPackage("..executions..")
                    .and().haveSimpleNameStartingWith("Test")
                    .should().dependOnClassesThat().areAnnotatedWith(Autowired.class)
                    .because("The autowired annotation should not be used out of test classes");

}
