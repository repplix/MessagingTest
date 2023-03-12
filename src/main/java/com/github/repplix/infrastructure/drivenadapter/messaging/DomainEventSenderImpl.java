package com.github.repplix.infrastructure.drivenadapter.messaging;

import com.github.repplix.domain.TestDomainEvent;
import com.github.repplix.domainservice.DomainEventSender;
import io.jexxa.addend.infrastructure.DrivenAdapter;
import io.jexxa.infrastructure.messaging.MessageSender;

import java.util.Objects;
import java.util.Properties;

import static io.jexxa.infrastructure.MessageSenderManager.getMessageSender;


@SuppressWarnings("unused")
@DrivenAdapter
public class DomainEventSenderImpl implements DomainEventSender
{
    private final MessageSender messageSender;

    public DomainEventSenderImpl(Properties properties)
    {
        messageSender = getMessageSender(DomainEventSender.class, properties);
    }

    @Override
    public void publish(TestDomainEvent domainEvent)
    {
        Objects.requireNonNull(domainEvent);
        messageSender
                .send(domainEvent)
                .toTopic("MessagingTest")
                .addHeader("Type", domainEvent.getClass().getSimpleName())
                .asJson();
    }
}
