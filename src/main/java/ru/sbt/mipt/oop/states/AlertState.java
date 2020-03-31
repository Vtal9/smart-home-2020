package ru.sbt.mipt.oop.states;

import ru.sbt.mipt.oop.Alarm;

public class AlertState implements State {


    protected Alarm alarm;

    public AlertState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        // do nothing
    }

    @Override
    public void deactivate(String code) {
        if (alarm.codeIsCorrect(code)) {
            alarm.changeState(new InactiveState(alarm));
        } else {
            activateAlert();
        }
    }

    @Override
    public void activateAlert() {
        // do nothing
    }
}
