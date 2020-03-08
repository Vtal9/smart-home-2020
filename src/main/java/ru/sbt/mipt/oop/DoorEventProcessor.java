package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    public static void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        Door door = getDoorByID(smartHome, event);
        if (door != null) {
            if (event.getType() == DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " was closed.");
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                if (isHall(smartHome, door)) {
                    LightEventProcessor.TurnOffAllLights(smartHome);
                }
            }
        }
    }

    private static boolean isHall(SmartHome smartHome, Door door) {
        for (Room room : smartHome.getRooms()) {
            for (Door curDoor : room.getDoors()) {
                if (curDoor.equals(door) && room.getName().equals("hall")) {
                    return true;
                }
            }
        }
        return false;
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

}
