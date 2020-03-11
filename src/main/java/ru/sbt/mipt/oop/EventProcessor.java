package ru.sbt.mipt.oop;

public interface EventProcessor {
    void handleEvent(SmartHome smartHome, SensorEvent event);
}
