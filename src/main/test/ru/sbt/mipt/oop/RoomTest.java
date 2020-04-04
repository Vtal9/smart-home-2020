package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void executeLight() {
        Light light = new Light("1", false);
        Room room = new Room(Arrays.asList(light),
                Arrays.asList(new Door(false,"1")), "kitchen");
        room.execute(homeComponent->{
            if(homeComponent instanceof Light){
                ((Light) homeComponent).setOn(true);
            }
        });
        assertTrue(light.isOn());
    }

    @Test
    public void executeDoor() {
        Door door = new Door(false, "1");
        Room room = new Room(Arrays.asList(new Light("1", false)),
                Arrays.asList(door), "kitchen");
        room.execute(homeComponent->{
            if(homeComponent instanceof Door){
                ((Door) homeComponent).setOpen(true);
            }
        });
        assertTrue(door.isOpen());
    }
}