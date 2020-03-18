package ru.sbt.mipt.oop;

public abstract class State {
    protected Alarm alarm;

    public State(Alarm alarm) {
        this.alarm = alarm;
    }

    abstract public void activate(String code);

    abstract public void deactivate(String code);

    abstract public void activateAlert();
}
