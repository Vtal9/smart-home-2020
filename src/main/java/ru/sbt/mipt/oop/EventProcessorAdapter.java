package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventProcessorAdapter implements EventHandler {
    private EventProcessor processor;
    private SmartHome smartHome;

    EventProcessorAdapter(EventProcessor processor, SmartHome smartHome) {
        this.processor = processor;
        this.smartHome = smartHome;
    }

    private SensorEvent convertType(CCSensorEvent event) {
        SensorEvent newEvent = null;
        switch (event.getEventType()) {
            case ("LightIsOn"):
                newEvent = new SensorEvent(LIGHT_ON, event.getObjectId());
                break;
            case ("LightIsOff"):
                newEvent = new SensorEvent(LIGHT_OFF, event.getObjectId());
                break;
            case ("DoorIsOpen"):
                newEvent = new SensorEvent(DOOR_OPEN, event.getObjectId());
                break;
            case ("DoorIsClosed"):
                newEvent = new SensorEvent(DOOR_CLOSED, event.getObjectId());
                break;
        }
        return newEvent;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        processor.processEvent(smartHome, convertType(event));
    }
}
