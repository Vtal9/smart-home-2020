package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.Application.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    @Override
    public  void handleEvent(SmartHome smartHome, SensorEvent event) {

        if (isLightEvent(event)) {
            // событие от источника света
            Light light = getLightByID(smartHome, event);
            if (light != null) {
                light.setOn(event.getType() == LIGHT_ON);
                System.out.println("Light " + light.getId() + ((event.getType() == LIGHT_ON)
                        ? " was turned on."
                        : " was turned off."));

            }
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

    private static boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
