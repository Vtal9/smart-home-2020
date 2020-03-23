package ru.sbt.mipt.oop;

public class InactiveState implements State {

    protected Alarm alarm;

    public InactiveState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate() {
        alarm.changeState(new ActiveState(alarm));
    }

    @Override
    public void deactivate() {
        // do nothing
    }

    @Override
    public void activateAlert() {
        alarm.changeState(new ActiveState(alarm));
    }
}
