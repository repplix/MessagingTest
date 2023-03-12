package com.github.repplix;

import com.github.repplix.applicationservice.EventGeneratorService;
import com.github.repplix.infrastructure.drivingadapter.messaging.TestEventListener;
import io.jexxa.core.JexxaMain;
import io.jexxa.drivingadapter.messaging.JMSAdapter;
import io.jexxa.drivingadapter.rest.RESTfulRPCAdapter;
import io.jexxa.infrastructure.healthcheck.HealthIndicators;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class MessagingTest
{
    public static void main(String[] args)
    {
        var jexxaMain = new JexxaMain(MessagingTest.class);

        jexxaMain
                .bootstrap(EventGeneratorService.class).and()     // DomainEventService to forward DomainEvents to a message bus

                .bind(RESTfulRPCAdapter.class).to(EventGeneratorService.class)   // Provide REST access to BookStoreService
                .bind(RESTfulRPCAdapter.class).to(jexxaMain.getBoundedContext()) // Provide REST access to BoundedContext
                .bind(JMSAdapter.class).to(TestEventListener.class)

                .monitor(TestEventListener.class).with(HealthIndicators.timeoutIndicator(Duration.ofSeconds(10)))
                .logUnhealthyDiagnostics(13, TimeUnit.SECONDS)

                .run(); // Finally run the application
    }
}
