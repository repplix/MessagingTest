package com.github.repplix.infrastructure.drivingadapter.messaging;

import com.github.repplix.applicationservice.EventGeneratorService;
import com.github.repplix.domain.TestDomainEvent;
import io.jexxa.addend.infrastructure.DrivingAdapter;
import io.jexxa.drivingadapter.messaging.JMSConfiguration;
import io.jexxa.drivingadapter.messaging.listener.IdempotentListener;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

@DrivingAdapter
public class TestEventListener extends IdempotentListener<TestDomainEvent> {
    public TestEventListener(EventGeneratorService eventGeneratorService, Properties properties)
    {
        super(TestDomainEvent.class, properties);
    }

    @Override
    @JMSConfiguration(destination = "MessagingTest", messagingType = JMSConfiguration.MessagingType.TOPIC, sharedSubscriptionName = "MessagingTest")
    public void onMessage(TestDomainEvent message) {
        // We need just to receive the message
    }

    @Override
    protected Duration getStorageDuration()
    {
        return Duration.of(10, ChronoUnit.SECONDS);
    }
}
