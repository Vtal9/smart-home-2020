package ru.sbt.mipt.oop;

import static org.junit.Assert.assertFalse;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEvenProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        smartHome.execute(homeComponent -> {
            if (!(event.getType() == DOOR_CLOSED && homeComponent instanceof Room)) {
                return;
            }

            Room room = (Room) homeComponent;
            if (room.getName().equals("hall")) {
                room.getDoors().forEach(door -> {
                    if (door.getId().equals(event.getObjectId())) {
                        TurnOffAllLights(smartHome);
                    }
                });
            }

        });
    }

    private static void TurnOffAllLights(SmartHome smartHome) {
        smartHome.getRooms().forEach(homeRoom -> homeRoom.getLights().forEach(light -> {
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            CommandSender.sendCommand(command);
        }));
    }
}
