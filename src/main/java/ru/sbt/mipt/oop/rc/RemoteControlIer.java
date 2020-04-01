package ru.sbt.mipt.oop.rc;

import rc.RemoteControl;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteControlIer implements RemoteControl {
    private Map<String, Command> bindedCommands = new HashMap<>();

    public Command getCommand(String buttonCode){
        return bindedCommands.get(buttonCode);
    }

    public RemoteControlIer(List<String> binds) {
        for (String bind : binds) {
            this.bindedCommands.put(bind, new CommandEmpty());
        }
    }

    public void bindCommand(String buttonCode, Command command){
        bindedCommands.put(buttonCode, command);
    }


    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        bindedCommands.get(buttonCode).execute();
    }
}
