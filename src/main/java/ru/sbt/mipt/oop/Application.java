package ru.sbt.mipt.oop;

import java.util.Arrays;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        SmartHomeHandler homeHandler = new SmartHomeHandler(Arrays.asList(new DoorEventProcessor(),
                new LightEventProcessor(), new HallDoorEvenProcessor()));
        homeHandler.handle(smartHome);
    }

}
