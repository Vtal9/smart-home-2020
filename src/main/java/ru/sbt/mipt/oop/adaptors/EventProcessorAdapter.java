package ru.sbt.mipt.oop.adaptors;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.processors.EventProcessor;

import java.util.Map;


public class EventProcessorAdapter implements EventHandler {
    private EventProcessor processor;
    private SmartHome smartHome;

    private final Map<String, SensorEventType> typeMatch;

    public EventProcessorAdapter(EventProcessor processor, SmartHome smartHome,
                                 Map<String, SensorEventType> typeMatch) {
        this.processor = processor;
        this.smartHome = smartHome;
        this.typeMatch = typeMatch;
    }

    private SensorEvent convertType(CCSensorEvent event) {
        if (!typeMatch.containsKey(event.getEventType())) {
            return null;
        }
        return new SensorEvent(typeMatch.get(event.getEventType()), event.getObjectId());
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        processor.processEvent(smartHome, convertType(event));
    }
}
