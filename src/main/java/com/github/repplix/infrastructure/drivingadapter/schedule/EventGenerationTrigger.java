package com.github.repplix.infrastructure.drivingadapter.schedule;

import com.github.repplix.applicationservice.EventGeneratorService;
import io.jexxa.addend.infrastructure.DrivingAdapter;
import io.jexxa.drivingadapter.scheduler.Scheduled;

@DrivingAdapter
public class EventGenerationTrigger {
    private final EventGeneratorService eventGeneratorService;

    public EventGenerationTrigger(EventGeneratorService eventGeneratorService)
    {
        this.eventGeneratorService = eventGeneratorService;
    }

    @SuppressWarnings("unused")
    @Scheduled(fixedRate = 1)
    public void triggerEventGeneration()
    {
        eventGeneratorService.generateDomainEvent();
    }
}
