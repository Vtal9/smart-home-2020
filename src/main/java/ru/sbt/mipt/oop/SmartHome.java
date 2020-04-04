package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.components.Action;
import ru.sbt.mipt.oop.components.Actionable;
import ru.sbt.mipt.oop.components.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    Alarm alarm = new Alarm();

    public Alarm getAlarm(){
        return alarm;
    }

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
            action.act(this);
            action.act(alarm);
            rooms.forEach(room -> room.execute(action));
    }
}
