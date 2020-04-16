package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class CommandActivateAlertState implements Command {
    private final Alarm alarm;
    public CommandActivateAlertState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.activateAlert();
    }
}
