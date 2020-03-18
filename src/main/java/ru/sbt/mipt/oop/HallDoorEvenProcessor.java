package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEvenProcessor implements EventProcessor {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        if (event.getType() == DOOR_CLOSED && isHall(smartHome, event)) {
            TurnOffAllLights(smartHome);
        }
    }

    private static boolean isHall(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private static void TurnOffAllLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }
}
