package ru.sbt.mipt.oop;

import java.util.Arrays;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = SmartHomeReader.readSmartHome();

        AlarmProcessEventDecorator alarmProcessEventDecorator =
                new AlarmProcessEventDecorator(Arrays.asList(new DoorEventProcessor(),
                        new LightEventProcessor(), new HallDoorEvenProcessor(), new AlarmEventProcessor()));

        SmartHomeHandler homeHandler = new SmartHomeHandler(Arrays.asList(alarmProcessEventDecorator),
                new EventCreatorImpl());

        homeHandler.handle(smartHome);
    }

}
