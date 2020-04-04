package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processors.EventProcessor;

import java.util.List;

public class SmartHomeHandler {
    private final EventCreator eventCreator;
    private List<EventProcessor> processors;

    SmartHomeHandler(List<EventProcessor> processors, EventCreator eventCreator) {
        this.eventCreator = eventCreator;
        this.processors = processors;
    }

    public void handle(SmartHome smartHome) {
        SensorEvent event = eventCreator.getNextSensorEvent();
        // начинаем цикл обработки событий
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor processor : processors) {
                processor.processEvent(smartHome, event);
            }
            event = eventCreator.getNextSensorEvent();
        }
    }
}
