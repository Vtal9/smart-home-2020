package ru.sbt.mipt.oop.processors;

import org.springframework.context.annotation.Bean;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    @Bean
    EventProcessor DoorEventProcessor() {
        return new DoorEventProcessor();
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        if (isDoorsEvent(event)) {
            smartHome.execute(homeComponent -> {
                if (homeComponent instanceof Door) {
                    Door door = (Door) homeComponent;
                    if (door.getId().equals(event.getObjectId())) {
                        door.setOpen(event.getType() == DOOR_OPEN);
                        System.out.println("Door" + door.getId() +
                                (event.getType() == DOOR_OPEN ? " was opened" : " was closed"));
                    }
                }

            });
        }
    }

    private static boolean isDoorsEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
