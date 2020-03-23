package ru.sbt.mipt.oop;

public interface State {

    abstract public void activate();

    abstract public void deactivate();

    abstract public void activateAlert();
}
