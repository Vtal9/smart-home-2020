package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Light;

public class CommandTurnOffAllLights implements Command {
    private final SmartHome smartHome;

    public CommandTurnOffAllLights(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                ((Light) homeComponent).setOn(false);
            }
        });
    }
}
