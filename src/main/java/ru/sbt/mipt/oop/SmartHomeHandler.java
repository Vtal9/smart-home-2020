package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class SmartHomeHandler {

   private List<EventProcessor> processors;

   SmartHomeHandler(List<EventProcessor> processors){
       this.processors = processors;
   }

    public void handle(SmartHome smartHome){
        SensorEvent event = EventCreator.getNextSensorEvent();
        // начинаем цикл обработки событий
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor processor : processors) {
                processor.handleEvent(smartHome, event);
            }
            event = EventCreator.getNextSensorEvent();
        }
    }
}
