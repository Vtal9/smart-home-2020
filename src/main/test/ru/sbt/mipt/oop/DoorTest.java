package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;

import static org.junit.Assert.*;

public class DoorTest {

    @Test
    public void execute() {
        Door door = new Door(true, "1");
        door.execute(homeComponent -> {
            ((Door) homeComponent).setOpen(false);
        });
        assertFalse(door.isOpen());
    }
}