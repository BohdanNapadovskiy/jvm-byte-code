package ru.napadovskiu.storage;

import org.junit.Test;
import ru.napadovskiu.entities.GearBox;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GearBoxStorageTest {

    private GearBoxStorage store = GearBoxStorage.getInstance();

    @Test
    public void whenAddGearBoxThenReturnId() {
        GearBox gearBox = new GearBox();
        gearBox.setGearBoxName("Механическая");
        int id = store.add(gearBox);
        GearBox tmpGearBox = new GearBox(id);
        assertThat(id, is(tmpGearBox.getGearBoxId()));
    }


    @Test
    public void whenUpdateThenReturnTrue() {
        GearBox gearBox = new GearBox(1);
        gearBox.setGearBoxName("Роторная");
        boolean result =  store.update(gearBox);
        assertThat(result, is(true));

    }

    @Test
    public void whenDeleteThenReturnTrue() {
        GearBox gearBox = new GearBox();
        gearBox.setGearBoxName("Автоматическая");
        int id = store.add(gearBox);
        GearBox tmpGearBox = new GearBox(id);
        boolean result =  store.delete(tmpGearBox);
        assertThat(result, is(true));
    }

 }