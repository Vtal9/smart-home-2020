package ru.sbt.mipt.oop.rc;

import rc.RemoteControl;
import ru.sbt.mipt.oop.rc.commands.Command;

import java.util.Map;

public class RemoteControlIml implements RemoteControl {
    private Map<String, Command> bindedCommands;

    public Command getCommand(String buttonCode) {
        return bindedCommands.get(buttonCode);
    }

    public RemoteControlIml(Map<String, Command> bindedCommands) {
        this.bindedCommands = bindedCommands;
    }


    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!bindedCommands.containsKey(buttonCode)) {
            return;
        }
        bindedCommands.get(buttonCode).execute();
    }
}
