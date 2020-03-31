package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;

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


        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Door) {
                Door door = (Door) homeComponent;
                if (door.getId().equals(objectId)) {
                    assertFalse(door.isOpen());
                }
            }
        });
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

        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Door) {
                Door door = (Door) homeComponent;
                if (door.getId().equals(objectId)) {
                    assertTrue(door.isOpen());
                }
            }
        });
    }
}