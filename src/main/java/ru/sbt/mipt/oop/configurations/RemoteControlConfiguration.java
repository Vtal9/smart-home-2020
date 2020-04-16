package ru.sbt.mipt.oop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.rc.RemoteControlIml;
import ru.sbt.mipt.oop.rc.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RemoteControlConfiguration {
    @Bean
    RemoteControl remoteControl(List<Command> commands, Map<String, Command> binds) {
        for (Command command : commands) {
            for (String bind : binds.keySet()) {
                if (binds.get(bind) instanceof CommandEmpty) {
                    binds.put(bind, command);
                }
            }
        }
        return new RemoteControlIml(binds);
    }

    @Bean
    Map<String, Command> controllerButtonsBinds() {
        List<String> binds = Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");

        Map<String, Command> bindedCommands = new HashMap<>();
        for (String bind : binds) {
            bindedCommands.put(bind, new CommandEmpty());
        }
        return bindedCommands;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(RemoteControl controller, String rcId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(controller, rcId);
        return remoteControlRegistry;
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

}
