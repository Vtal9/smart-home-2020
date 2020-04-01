package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;

public class CommandTurnOnLightsInHall implements Command {
    private final SmartHome smartHome;

    public CommandTurnOnLightsInHall(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent ->{
            if(!(homeComponent instanceof Room)){
                return;
            }
            Room room = (Room) homeComponent;
            if(room.getName().equals("hall")) {
                room.execute(roomComponent->{
                    if (roomComponent instanceof Light) {
                        ((Light) roomComponent).setOn(true);
                    }
                });
            }
        });
    }
}
