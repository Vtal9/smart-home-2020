package ru.sbt.mipt.oop.alarm.states;

import ru.sbt.mipt.oop.alarm.Alarm;

public class InactiveState implements State {

    protected Alarm alarm;

    public InactiveState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.changeState(new ActiveState(alarm));
    }

    @Override
    public void deactivate(String code) {
        // do nothing
    }

    @Override
    public void activateAlert() {
        alarm.changeState(new ActiveState(alarm));
    }
}
