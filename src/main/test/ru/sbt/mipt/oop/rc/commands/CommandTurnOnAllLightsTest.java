package ru.sbt.mipt.oop.rc.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.components.Light;

import static org.junit.Assert.*;

public class CommandTurnOnAllLightsTest {

    @Test
    public void turnOnALlLights() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        Command command = new CommandTurnOnAllLights(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                assertTrue(((Light) homeComponent).isOn());
            }
        });
    }
}