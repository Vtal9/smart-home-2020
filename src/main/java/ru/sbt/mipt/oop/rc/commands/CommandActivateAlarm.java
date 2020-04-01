package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class CommandActivateAlarm implements Command {
    private final Alarm alarm;
    private final String code;
    public CommandActivateAlarm(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void execute() {
        alarm.activate(this.code);
    }
}
