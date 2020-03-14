package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEvenProcessorTest {

    @Test
    public void processEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String objectId = "4";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, objectId);

        HallDoorEvenProcessor processor = new HallDoorEvenProcessor();
        processor.processEvent(smartHome, event);

        DoorEventProcessor doorProcessor = new DoorEventProcessor();
        doorProcessor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    @Test
    public void noHallDoorEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorEvent(LIGHT_ON, objectId);
        LightEventProcessor lightProcessor = new LightEventProcessor();
        lightProcessor.processEvent(smartHome, event);

        objectId = "3";
        event = new SensorEvent(DOOR_CLOSED, objectId);
        HallDoorEvenProcessor hallDoorProcessor = new HallDoorEvenProcessor();
        hallDoorProcessor.processEvent(smartHome, event);

        DoorEventProcessor doorProcessor = new DoorEventProcessor();
        doorProcessor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(objectId)) {
                    assertTrue(light.isOn());
                }
            }
        }
    }
}