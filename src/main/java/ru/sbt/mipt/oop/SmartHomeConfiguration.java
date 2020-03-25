package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class SmartHomeConfiguration {
    @Bean
    public SmartHome smartHome() {
        return SmartHomeReader.readSmartHome();
    }

    @Bean
    public EventProcessor processor(SmartHome smartHome) {
        return new AlarmProcessEventDecorator(Arrays.asList(new DoorEventProcessor(),
                new LightEventProcessor(), new HallDoorEvenProcessor(), new AlarmEventProcessor()));

    }

    @Bean
    public SensorEventsManager sensorEventsManager(EventProcessor eventProcessor, SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new EventProcessorAdapter(eventProcessor, smartHome));
        return sensorEventsManager;
    }
}


