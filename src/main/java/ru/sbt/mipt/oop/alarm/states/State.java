package ru.sbt.mipt.oop.alarm.states;

public interface State {

    public void activate(String code);

    public void deactivate(String code);

    public void activateAlert();
}
