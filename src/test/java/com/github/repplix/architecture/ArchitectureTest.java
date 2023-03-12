package com.github.repplix.architecture;

import com.github.repplix.MessagingTest;
import org.junit.jupiter.api.Test;

import static io.jexxa.jexxatest.architecture.ArchitectureRules.aggregateRules;
import static io.jexxa.jexxatest.architecture.ArchitectureRules.patternLanguage;
import static io.jexxa.jexxatest.architecture.ArchitectureRules.portsAndAdapters;

/**
 * This test can be used to validate the architecture of your application
 * <p>
 * You have only to adjust test validatePortsAndAdapters. Here you have to add all your
 * packages containing the driven and driving adapters. This information is required to ensure
 * that there are no dependencies between these packages.
 */
class ArchitectureTest {

    @Test
    void validatePortsAndAdapters()
    {
        portsAndAdapters(MessagingTest.class)
                // Add all packages providing driven adapter
                .addDrivenAdapterPackage("messaging")

                // Add all packages providing driving adapter such as
                .addDrivingAdapterPackage("messaging")
                .addDrivingAdapterPackage("schedule")

                .validate();
    }

    @Test
    void validatePatternLanguage()
    {
        patternLanguage(MessagingTest.class).validate();
    }

    @Test
    void validateAggregateRules()
    {
        aggregateRules(MessagingTest.class).validate();
    }
}