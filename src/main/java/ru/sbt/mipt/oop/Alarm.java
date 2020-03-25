package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.states.InactiveState;
import ru.sbt.mipt.oop.states.State;

public class Alarm implements Actionable {

    private State state;

    private String code;

    public boolean codeIsCorrect(String code) {
        return this.code.equals(code);
    }

    boolean isActive() {
        return !(state instanceof InactiveState);
    }

    public Alarm() {
        state = new InactiveState(this);
    }

    public void changeState(State newState) {
        state = newState;
    }


    public void activate(String code) {
        this.code = code;
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void activateAlert() {
        state.activateAlert();
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
