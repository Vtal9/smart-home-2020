package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmEventProcessor implements EventProcessor {
    private String code;


    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isAlarmEvent(event)) {
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Alarm) {
                    Alarm alarm = (Alarm) homeComponent;
                    if (event.getType() == ALARM_ACTIVATE) {
                        this.code = event.getObjectId();
                        alarm.activate();
                    } else {
                        if (this.code.equals(event.getObjectId())) {
                            alarm.deactivate();
                        }
                    }
                }
            });
        }
    }

    boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE;
    }
}
