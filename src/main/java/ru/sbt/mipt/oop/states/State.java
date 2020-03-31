package ru.sbt.mipt.oop.states;

public interface State {

    abstract public void activate(String code);

    abstract public void deactivate(String code);

    abstract public void activateAlert();
}
