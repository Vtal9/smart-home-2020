package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmProcessEventDecorator implements EventProcessor {
    private EventProcessor eventProcessor;

    public AlarmProcessEventDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != ALARM_DEACTIVATE && smartHome.getAlarm().isActive()) {
            smartHome.getAlarm().activateAlert();
            System.out.println("Sending sms");
        } else {
            eventProcessor.processEvent(smartHome, event);
        }
    }
}
