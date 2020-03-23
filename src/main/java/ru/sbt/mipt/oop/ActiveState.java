package ru.sbt.mipt.oop;

public class ActiveState implements State {

    protected Alarm alarm;

    public ActiveState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate() {
        //do nothing
    }

    @Override
    public void deactivate() {
        alarm.changeState(new InactiveState(alarm));
    }

    @Override
    public void activateAlert() {
        alarm.changeState(new AlertState(alarm));
    }
}
