package ru.sbt.mipt.oop.rc;

import org.junit.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandCloseHallDoor;
import ru.sbt.mipt.oop.rc.commands.CommandEmpty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RemoteControlImlTest {

    @Test
    public void bindCommandAndCheck() {
        List<String> binds = Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");

        Map<String, Command> bindedCommands = new HashMap<>();
        for (String bind : binds) {
            bindedCommands.put(bind, new CommandEmpty());
        }

        SmartHome smartHome = SmartHomeReader.readSmartHome();

        bindedCommands.put("A", new CommandCloseHallDoor(smartHome));


        RemoteControlIml controller = new RemoteControlIml(bindedCommands);
        assertTrue(controller.getCommand("A") instanceof CommandCloseHallDoor);
    }

    @Test
    public void bindCommandAndPressButton() {
        List<String> binds = Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");

        Map<String, Command> bindedCommands = new HashMap<>();
        for (String bind : binds) {
            bindedCommands.put(bind, new CommandEmpty());
        }

        SmartHome smartHome = SmartHomeReader.readSmartHome();

        bindedCommands.put("A", new CommandCloseHallDoor(smartHome));


        RemoteControlIml controller = new RemoteControlIml(bindedCommands);

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