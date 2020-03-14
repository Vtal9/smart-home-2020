package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SmartHomeTest {
    @Test
    public void addRoom() {
        SmartHome smartHome = new SmartHome();
        Room newRoom = new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(new Door(true, "1")), "kitchen");
        smartHome.addRoom(newRoom);
        smartHome.getRooms().forEach(room -> {
            assertEquals(room, newRoom);
        });
    }

    @Test
    public void execute() {
        SmartHome smartHome = new SmartHome();
        Room newRoom = new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(new Door(true, "1")), "kitchen");
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof SmartHome){
                ((SmartHome) homeComponent).addRoom(newRoom);
            }
        });

        smartHome.getRooms().forEach(room -> {
            assertEquals(room, newRoom);
        });
    }
}