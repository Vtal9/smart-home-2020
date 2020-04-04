package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AlarmEventProcessor implements EventProcessor {




    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isAlarmEvent(event)) {
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Alarm) {
                    Alarm alarm = (Alarm) homeComponent;
                    if (event.getType() == ALARM_ACTIVATE) {
                        alarm.activate(event.getObjectId());
                    } else {
                        alarm.deactivate(event.getObjectId());
                    }
                }
            });
        }
    }

    boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE;
    }
}
