package ru.sbt.mipt.oop.rc.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.components.Light;

import static org.junit.Assert.*;

public class CommandTurnOffAllLightsTest {

    @Test
    public void turnOffAllLights() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        Command command = new CommandTurnOffAllLights(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                assertFalse(((Light) homeComponent).isOn());
            }
        });
    }
}