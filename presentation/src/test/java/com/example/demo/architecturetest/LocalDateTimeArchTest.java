package com.example.demo.architecturetest;

import com.example.demo.DateTimeWrapper;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class LocalDateTimeArchTest {

    /*
    This rules is added so when you want to test a class with a localDateTime
    you can easily set a fixed instant of time. This helps you avoiding to mock a static method of LocalDateTime.now()
     */

    @Test
    void checkIfNobodyDirectlyCallsDateTimeWrapper() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.example.demo");

        ArchRule rule = noClasses()
        .that(are(not(equivalentTo(DateTimeWrapper.class))))
                .should().callMethod(LocalDateTime.class, "now");

        rule.check(importedClasses);
    }
}
