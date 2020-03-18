package ru.sbt.mipt.oop;

public class InactiveState extends State {

    public InactiveState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        alarm.changeState(new ActiveState(alarm));
        alarm.setCode(code);
    }

    @Override
    public void deactivate(String code) {
        // do nothing
    }

    @Override
    public void activateAlert() {
        // do nothing
    }
}
