package ru.sbt.mipt.oop.rc.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.alarm.Alarm;

import static org.junit.Assert.*;

public class CommandActivateAlarmTest {

    @Test
    public void execute() {
        Alarm alarm = new Alarm();
        Command command = new CommandActivateAlarm(alarm, "code");
        command.execute();
        assertTrue(alarm.isActive());
    }
}