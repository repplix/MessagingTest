package com.github.repplix.domainservice;


import com.github.repplix.domain.DomainEventPublisher;
import com.github.repplix.domain.TestDomainEvent;
import io.jexxa.addend.applicationcore.DomainEventHandler;
import io.jexxa.addend.applicationcore.DomainService;

@SuppressWarnings("unused")
@DomainService
public class DomainEventService
{
    private final DomainEventSender domainEventSender;
    @SuppressWarnings("unused")
    public DomainEventService(DomainEventSender domainEventSender)
    {
        this.domainEventSender = domainEventSender;
        DomainEventPublisher.subscribe(TestDomainEvent.class, this::handleEvent);
    }

    @DomainEventHandler
    public void handleEvent(TestDomainEvent domainEvent)
    {
        domainEventSender.publish(domainEvent);
    }
}
