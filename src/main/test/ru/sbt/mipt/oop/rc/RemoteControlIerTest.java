package ru.sbt.mipt.oop.rc;

import org.junit.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.rc.commands.CommandCloseHallDoor;
import ru.sbt.mipt.oop.rc.commands.CommandEmpty;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RemoteControlIerTest {

    @Test
    public void bindCommandAndCheck() {
        RemoteControlIer controller = new RemoteControlIer(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4"));
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        controller.bindCommand("A", new CommandCloseHallDoor(smartHome));
        assertTrue(controller.getCommand("A") instanceof CommandCloseHallDoor);
    }

    @Test
    public void bindCommandAndPressButton() {
        RemoteControlIer controller = new RemoteControlIer(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4"));
        SmartHome smartHome = SmartHomeReader.readSmartHome();

        //manually open the hall door
        smartHome.execute(homeComponent -> {
            if (!(homeComponent instanceof Room)) {
                return;
            }

            Room room = (Room) homeComponent;
            if (room.getName().equals("hall")) {
                room.execute(roomComponent -> {
                    if (!(roomComponent instanceof Door)) {
                        return;
                    }
                    Door door = (Door) roomComponent;
                    door.setOpen(true);
                });
            }
        });

        controller.bindCommand("A", new CommandCloseHallDoor(smartHome));
        controller.onButtonPressed("A", "someController");

        smartHome.execute(homeComponent -> {
            if (!(homeComponent instanceof Room)) {
                return;
            }

            Room room = (Room) homeComponent;
            if (room.getName().equals("hall")) {
                room.execute(roomComponent -> {
                    if (!(roomComponent instanceof Door)) {
                        return;
                    }
                    Door door = (Door) roomComponent;
                    assertFalse(door.isOpen());
                });
            }
        });
    }
}