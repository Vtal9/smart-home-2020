package ru.sbt.mipt.oop;

public class Alarm  implements  Actionable{

    private State state;

    boolean isActive(){
        return !(state instanceof InactiveState);
    }

    public Alarm(){
        state = new InactiveState(this);
    }

    public void changeState(State newState){
        state = newState;
    }


    public void activate(){
        state.activate();
    }

    public void deactivate(){
        state.deactivate();
    }

    public void activateAlert(){
        state.activateAlert();
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
