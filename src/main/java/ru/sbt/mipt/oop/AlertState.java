package ru.sbt.mipt.oop;

public class AlertState extends State {


    public AlertState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        // do nothing
    }

    @Override
    public void deactivate(String code) {
        if(code.equals(alarm.getCode())){
            alarm.changeState(new InactiveState(alarm));
        }
    }

    @Override
    public void activateAlert() {

    }
}
