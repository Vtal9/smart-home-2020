package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;


public class AlarmEventProcessorTest {

    @Test
    public void processEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String code = "qwerty";

        SensorEvent event = new SensorEvent(ALARM_ACTIVATE, code);
        EventProcessor processor = new AlarmEventProcessor();
        processor = new AlarmProcessEventDecorator(processor);
        processor.processEvent(smartHome, event);


        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Alarm) {
                Alarm alarm = (Alarm) homeComponent;
                assertTrue(alarm.isActive());

            }
        });
    }


    @Test
    public void correctCode() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String code = "qwerty";

        EventProcessor processor = new AlarmEventProcessor();
        processor = new AlarmProcessEventDecorator(processor);

        SensorEvent event = new SensorEvent(ALARM_ACTIVATE, code);

        processor.processEvent(smartHome, event);


        event = new SensorEvent(ALARM_DEACTIVATE, code);

        processor.processEvent(smartHome, event);

        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Alarm) {
                Alarm alarm = (Alarm) homeComponent;
                assertFalse(alarm.isActive());

            }
        });
    }

    @Test
    public void wrongCode() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String code = "qwerty";

        EventProcessor processor = new AlarmEventProcessor();
        processor = new AlarmProcessEventDecorator(processor);

        SensorEvent event = new SensorEvent(ALARM_ACTIVATE, code);

        processor.processEvent(smartHome, event);

        code = "wrong code";
        event = new SensorEvent(ALARM_DEACTIVATE, code);

        processor.processEvent(smartHome, event);

        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Alarm) {
                Alarm alarm = (Alarm) homeComponent;
                assertTrue(alarm.isActive());
            }
        });
    }
}