package com.github.repplix.domain;

import io.jexxa.addend.applicationcore.DomainEvent;

import java.time.Instant;

@DomainEvent
public record TestDomainEvent(Instant time) {
    public static TestDomainEvent newTestEvent() { return new TestDomainEvent(Instant.now());}
}
