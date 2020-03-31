package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.processors.LightEventProcessor;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventProcessorTest {

    @Test
    public void processEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorEvent(LIGHT_OFF, objectId);
        LightEventProcessor processor = new LightEventProcessor();
        processor.processEvent(smartHome, event);
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                Light light = (Light) homeComponent;
                if (light.getId().equals(objectId)) {
                    assertFalse(light.isOn());
                }
            }
        });
    }

    @Test
    public void onAndThenOffEvent() {
        SmartHome smartHome = SmartHomeReader.readSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorEvent(LIGHT_OFF, objectId);
        LightEventProcessor processor = new LightEventProcessor();
        processor.processEvent(smartHome, event);
        event = new SensorEvent(LIGHT_ON, objectId);
        processor.processEvent(smartHome, event);

        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                Light light = (Light) homeComponent;
                if (light.getId().equals(objectId)) {
                    assertTrue(light.isOn());
                }
            }
        });
    }
}