package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processors.EventProcessor;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmProcessEventDecorator implements EventProcessor {
    private List<EventProcessor> processors;

    public AlarmProcessEventDecorator(List<EventProcessor> eventProcessors) {
        this.processors = eventProcessors;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != ALARM_DEACTIVATE && smartHome.getAlarm().isActive()) {
            smartHome.getAlarm().activateAlert();
            System.out.println("Sending sms");
        } else {
            for (EventProcessor processor : processors) {
                processor.processEvent(smartHome, event);
            }
        }
    }
}
