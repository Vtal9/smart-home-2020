package ru.sbt.mipt.oop.configurations;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.AlarmProcessEventDecorator;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;
import ru.sbt.mipt.oop.adaptors.EventProcessorAdapter;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.rc.RemoteControlIer;
import ru.sbt.mipt.oop.rc.commands.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.SensorEventType.*;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
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
    public EventProcessor processor(List<EventProcessor> processors) {
        return new AlarmProcessEventDecorator(processors);
    }

    @Bean
    EventProcessorAdapter eventProcessorAdapter(EventProcessor eventProcessor, SmartHome smartHome, Map<String, SensorEventType> typeMap) {
        return new EventProcessorAdapter(eventProcessor, smartHome, typeMap);
    }

    @Bean
    public SensorEventsManager sensorEventsManager(EventProcessorAdapter adapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adapter);
        return sensorEventsManager;
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
    Command commandActivateAlarm(Alarm alarm, String code) {
        return new CommandActivateAlarm(alarm, code);
    }

    @Bean
    String code() {
        return "code";
    }

    @Bean
    Command commandActivateAlertState(Alarm alarm) {
        return new CommandActivateAlertState(alarm);
    }

    @Bean
    Command commandCloseHallDoor(SmartHome smartHome) {
        return new CommandCloseHallDoor(smartHome);
    }

    @Bean
    Command commandTurnOnAllLights(SmartHome smartHome) {
        return new CommandTurnOnAllLights(smartHome);
    }

    @Bean
    Command commandTurnOffAllLights(SmartHome smartHome) {
        return new CommandTurnOffAllLights(smartHome);
    }

    @Bean
    Command commandTurnOnLightsInHall(SmartHome smartHome) {
        return new CommandTurnOnLightsInHall(smartHome);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    Alarm alarm() {
        return new Alarm();
    }

    @Bean
    RemoteControl remoteControl(List<Command> commands, List<String> binds) {
        RemoteControlIer controller = new RemoteControlIer(binds);
        for (Command command : commands) {
            for (String bind : binds) {
                if(controller.getCommand(bind) instanceof CommandEmpty){
                    controller.bindCommand(bind, command);
                }
            }
        }
        return controller;
    }

    @Bean
    List<String> binds() {
        return Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(RemoteControl controller, String rcId){
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(controller, rcId);
        return remoteControlRegistry;
    }

}


