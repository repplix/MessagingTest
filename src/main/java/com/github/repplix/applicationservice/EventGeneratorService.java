package com.github.repplix.applicationservice;

import com.github.repplix.domain.DomainEventPublisher;
import io.jexxa.addend.applicationcore.ApplicationService;

import static com.github.repplix.domain.TestDomainEvent.newTestEvent;

@SuppressWarnings("unused")
@ApplicationService
public class EventGeneratorService
{
    public void generateDomainEvent()
    {
        DomainEventPublisher.publish(newTestEvent());
    }
}
