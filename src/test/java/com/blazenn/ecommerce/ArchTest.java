package com.blazenn.ecommerce;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.blazenn.ecommerce");

        noClasses()
            .that()
                .resideInAnyPackage("com.blazenn.ecommerce.service..")
            .or()
                .resideInAnyPackage("com.blazenn.ecommerce.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.blazenn.ecommerce.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
