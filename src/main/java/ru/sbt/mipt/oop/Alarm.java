package ru.sbt.mipt.oop;

public class Alarm  implements  Actionable{
    private String code;

    protected String getCode(){
        return code;
    }

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


    public void activate(String code){
        state.activate(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void deactivate(String code){
        state.deactivate(code);
    }

    public void activateAlert(){
        state.activateAlert();
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
