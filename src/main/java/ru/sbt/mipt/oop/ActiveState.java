package ru.sbt.mipt.oop;

public class ActiveState extends State{

    public ActiveState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        //do nothing
    }

    @Override
    public void deactivate(String code) {
        if(code.equals(alarm.getCode())){
            alarm.changeState(new InactiveState(alarm));
        }
        else{
            activateAlert();
        }
    }

    @Override
    public void activateAlert() {
        alarm.changeState(new AlertState(alarm));
    }
}
