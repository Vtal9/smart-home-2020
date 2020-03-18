package ru.sbt.mipt.oop;

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
                System.out.println(processor);
                processor.processEvent(smartHome, event);
            }
            event = eventCreator.getNextSensorEvent();
        }
    }
}
