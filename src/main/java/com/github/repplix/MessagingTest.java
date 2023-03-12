package com.github.repplix;

import com.github.repplix.domainservice.DomainEventService;
import com.github.repplix.infrastructure.drivingadapter.messaging.TestEventListener;
import com.github.repplix.infrastructure.drivingadapter.schedule.EventGenerationTrigger;
import io.jexxa.adapterapi.invocation.InvocationContext;
import io.jexxa.adapterapi.invocation.monitor.BeforeMonitor;
import io.jexxa.common.wrapper.logger.SLF4jLogger;
import io.jexxa.core.JexxaMain;
import io.jexxa.drivingadapter.messaging.JMSAdapter;
import io.jexxa.drivingadapter.rest.RESTfulRPCAdapter;
import io.jexxa.drivingadapter.scheduler.Scheduler;
import io.jexxa.infrastructure.healthcheck.HealthIndicators;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class MessagingTest
{
    public static void main(String[] args)
    {
        var jexxaMain = new JexxaMain(MessagingTest.class);

        jexxaMain
                .bootstrap(DomainEventService.class).and()     // DomainEventService to forward DomainEvents to a message bus

                .bind(RESTfulRPCAdapter.class).to(jexxaMain.getBoundedContext()) // Provide REST access to BoundedContext
                .bind(JMSAdapter.class).to(TestEventListener.class)
                .bind(Scheduler.class).to(EventGenerationTrigger.class)

                .monitor(TestEventListener.class).with(HealthIndicators.timeoutIndicator(Duration.ofSeconds(10)))
                .monitor(TestEventListener.class).with(new MessageCounter())
                .logUnhealthyDiagnostics(13, TimeUnit.SECONDS)

                .run(); // Finally run the application
    }

    public static class MessageCounter extends BeforeMonitor {
        private int receivedMessage;

        @Override
        public void before(InvocationContext invocationContext) {
            ++receivedMessage;

            if (receivedMessage % 1000 == 0)
            {
                SLF4jLogger.getLogger(MessagingTest.class).warn("Received messages: {}", receivedMessage);
            }
        }

        @Override
        public boolean healthy() {
            return true;
        }

        @Override
        public String getStatusMessage() {
            return "Received messages: " + receivedMessage;
        }
    }

}
