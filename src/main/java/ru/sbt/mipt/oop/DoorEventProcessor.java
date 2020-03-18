package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        if (isDoorsEvent(event)) {
            Door door = getDoorByID(smartHome, event);
            if (door != null) {
                if (event.getType() == DOOR_OPEN) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was opened.");
                } else {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " was closed.");
                }
            }
        }
    }


    private static Door getDoorByID(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    return door;
                }
            }
        }
        return null;
    }

    private static boolean isDoorsEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
