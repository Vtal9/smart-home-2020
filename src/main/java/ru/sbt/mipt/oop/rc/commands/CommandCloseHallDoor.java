package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;

public class CommandCloseHallDoor implements Command {
    private final SmartHome smartHome;

    public CommandCloseHallDoor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent -> {
            if (!(homeComponent instanceof Room)) {
                return;
            }

            Room room = (Room) homeComponent;
            if (room.getName().equals("hall")) {
                room.execute(hallComponent -> {
                    if (hallComponent instanceof Door) {
                        Door door = (Door) hallComponent;
                        door.setOpen(false);
                    }
                });
            }

        });
    }
}
