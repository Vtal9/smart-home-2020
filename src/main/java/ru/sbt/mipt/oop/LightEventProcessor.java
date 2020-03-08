package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.Application.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    public static void HandleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        Light light = getLightByID(smartHome, event);
        if (light != null) {
            light.setOn(event.getType() == LIGHT_ON);
            System.out.println("Light " + light.getId() + ((event.getType() == LIGHT_ON)
                    ? " was turned on."
                    : " was turned off."));

        }
    }

    private static Light getLightByID(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    return light;
                }
            }
        }
        return null;
    }

    public static void TurnOffAllLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }
}
