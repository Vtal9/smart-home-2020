package ru.sbt.mipt.oop;

public class AlertState implements State {


    protected Alarm alarm;

    public AlertState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate() {
        // do nothing
    }

    @Override
    public void deactivate() {
        alarm.changeState(new InactiveState(alarm));
    }

    @Override
    public void activateAlert() {
        // do nothing
    }
}
