package com.github.repplix;

import com.github.repplix.domainservice.ReferenceLibrary;
import io.jexxa.core.JexxaMain;
import io.jexxa.drivingadapter.rest.RESTfulRPCAdapter;
import com.github.repplix.applicationservice.BookStoreService;
import com.github.repplix.domainservice.DomainEventService;

public final class MessagingTest
{
    public static void main(String[] args)
    {
        var jexxaMain = new JexxaMain(MessagingTest.class);

        jexxaMain
                .bootstrap(ReferenceLibrary.class).and()       // Bootstrap latest book via ReferenceLibrary
                .bootstrap(DomainEventService.class).and()     // DomainEventService to forward DomainEvents to a message bus

                .bind(RESTfulRPCAdapter.class).to(BookStoreService.class)        // Provide REST access to BookStoreService
                .bind(RESTfulRPCAdapter.class).to(jexxaMain.getBoundedContext()) // Provide REST access to BoundedContext

                .run(); // Finally run the application
    }
}
