package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessorTest {

    @Test
    public void processEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String objectId = "1";

        SensorEvent event = new SensorEvent(DOOR_CLOSED, objectId);
        DoorEventProcessor processor = new DoorEventProcessor();
        processor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(objectId)) {
                    assertFalse(door.isOpen());
                }
            }
        }
    }


    @Test
    public void openAndCLoseEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String objectId = "1";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, objectId);

        DoorEventProcessor processor = new DoorEventProcessor();
        processor.processEvent(smartHome, event);

        event = new SensorEvent(DOOR_OPEN, objectId);
        processor.processEvent(smartHome, event);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(objectId)) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }
}