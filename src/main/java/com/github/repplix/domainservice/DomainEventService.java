package com.github.repplix.domainservice;


import com.github.repplix.domain.DomainEventPublisher;
import com.github.repplix.domain.book.BookSoldOut;
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
        DomainEventPublisher.subscribe(BookSoldOut.class, this::handleEvent);
    }

    @DomainEventHandler
    public void handleEvent(BookSoldOut domainEvent)
    {
        domainEventSender.publish(domainEvent);
    }
}
