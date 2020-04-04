package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Light;

import static org.junit.Assert.*;

public class LightTest {

    @Test
    public void execute() {
        Light light = new Light("1", false);
        light.execute(homeComponent->{
            ((Light) homeComponent).setOn(true);
        });
        assertTrue(light.isOn());
    }
}