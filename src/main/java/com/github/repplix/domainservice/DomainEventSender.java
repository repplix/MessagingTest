package com.github.repplix.domainservice;


import com.github.repplix.domain.book.BookSoldOut;
import io.jexxa.addend.applicationcore.InfrastructureService;

@InfrastructureService
public interface DomainEventSender
{
    void publish(BookSoldOut domainEvent);
}
