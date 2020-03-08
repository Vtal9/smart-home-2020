package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = readSmartHome();
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        while (event != null) {//добавить extention point для добавления других событий
            System.out.println("Got event: " + event);

            if (isLightEvent(event)) {
                LightEventProcessor.HandleEvent(smartHome, event);
            }
            if (isDoorsEvent(event)) {
                DoorEventProcessor.handleEvent(smartHome, event);
            }
            event = getNextSensorEvent();
        }
    }

    private static boolean isDoorsEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

    private static boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }

    private static SmartHome readSmartHome() throws IOException {//лучше в отдельный класс
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
