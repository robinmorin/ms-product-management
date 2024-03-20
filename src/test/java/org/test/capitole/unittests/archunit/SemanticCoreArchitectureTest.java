package org.test.capitole.unittests.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/***
 * Checking Semantic Package Rules Architecture Test (For complying Rules Arq.Hexa/Clean.Arq)
 */
@AnalyzeClasses(packages = "org.test.capitole")
public class SemanticCoreArchitectureTest {

    @ArchTest
    static final ArchRule domainClassesMustResideInCorePackage =
            classes().that().haveNameMatching(".*Domain")
                     .should().resideInAPackage("..core..")
                     .as("Domain's class should reside inside core package ");

    @ArchTest
    static final ArchRule useCaseClassesMustResideInUseCasePackage =
            classes().that().haveNameMatching(".*UseCase").or().haveNameMatching(".*Interactor")
                            .should().resideInAPackage("..usecase..")
                            .as("UseCase's (Interactor) class should reside inside use case package ");

    @ArchTest
    static final ArchRule exceptionClassesMustResideInExceptionPackage =
            classes().that().haveNameMatching(".*Exception")
                     .should().resideInAPackage("..exception..")
                     .allowEmptyShould(true)
                     .as("Exception's class should reside inside exception package ");

    @ArchTest
    static final ArchRule gatewayClassesMustResideInPortPackage =
            classes().that().haveNameMatching(".*Gateway")
                     .should().resideInAPackage("..port..")
                     .andShould().beInterfaces()
                     .as("Gateway's interfaces should reside inside gateway package as interfaces ");

    @ArchTest
    static final ArchRule mapperClassesMustResideInMapperPackage =
        classes().that().haveNameMatching(".*Mapper").should().resideInAPackage("..mapper..")
            .as("Mapper's class should reside inside type package on core package ");

}