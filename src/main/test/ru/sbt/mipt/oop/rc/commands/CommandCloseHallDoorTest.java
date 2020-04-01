package ru.sbt.mipt.oop.rc.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;

import static org.junit.Assert.*;

public class CommandCloseHallDoorTest {

    @Test
    public void CloseHallDoor() {
        SmartHome  smartHome = SmartHomeReader.readSmartHome();
        Command command = new CommandCloseHallDoor(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(!(homeComponent instanceof Room)){
                return;
            }

            Room room = (Room) homeComponent;
            if(room.getName().equals("hall")){
                room.execute(roomComponent ->{
                    if(!(roomComponent instanceof Door)){
                        return;
                    }
                    Door door = (Door) roomComponent;
                    assertFalse(door.isOpen());
                });
            }
        });
    }
}