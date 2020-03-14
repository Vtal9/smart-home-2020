package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if (isLightEvent(event)) {
            // событие от источника света
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Light) {
                    Light light = (Light) homeComponent;
                    System.out.println(light);
                    if (light.getId().equals(event.getObjectId())) {
                        light.setOn(event.getType() == LIGHT_ON);
                        System.out.println("Light " + light.getId() + ((event.getType() == LIGHT_ON)
                                ? " was turned on."
                                : " was turned off."));
                    }
                }

            });

        }
    }

    private static boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
