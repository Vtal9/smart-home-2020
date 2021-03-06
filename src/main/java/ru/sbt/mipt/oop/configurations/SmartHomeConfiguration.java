package ru.sbt.mipt.oop.configurations;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.AlarmProcessEventDecorator;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.adaptors.EventProcessorAdapter;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.processors.*;

import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.SensorEventType.*;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    public SmartHome smartHome() {
        return SmartHomeReader.readSmartHome();
    }

    @Bean
    EventProcessor doorEventProcessor() {
        return new DoorEventProcessor();
    }

    @Bean
    EventProcessor lightEventProcessor() {
        return new LightEventProcessor();
    }

    @Bean
    EventProcessor hallDoorProcessor() {
        return new HallDoorEvenProcessor();
    }

    @Bean
    EventProcessor alarmEventProcessor() {
        return new AlarmEventProcessor();
    }

    @Bean
    Map<String, SensorEventType> typeMap() {
        return Map.of(
                "LightIsOn", LIGHT_ON,
                "LightIsOff", LIGHT_OFF,
                "DoorIsOpen", DOOR_OPEN,
                "DoorIsClosed", DOOR_CLOSED
        );
    }

    @Bean
    EventProcessorAdapter eventProcessorAdapter(List<EventProcessor> processors, SmartHome smartHome) {
        return new EventProcessorAdapter(new AlarmProcessEventDecorator(processors), smartHome, typeMap());
    }

    @Bean
    public SensorEventsManager sensorEventsManager(EventProcessorAdapter adapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adapter);
        return sensorEventsManager;
    }


    @Bean
    Alarm alarm() {
        return new Alarm();
    }

}


