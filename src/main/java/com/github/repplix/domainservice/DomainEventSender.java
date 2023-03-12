package com.github.repplix.domainservice;


import com.github.repplix.domain.TestDomainEvent;
import io.jexxa.addend.applicationcore.InfrastructureService;

@InfrastructureService
public interface DomainEventSender
{
    void publish(TestDomainEvent domainEvent);
}
