package ru.napadovskiu.storage;

import org.junit.Test;
import ru.napadovskiu.entities.Item;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemStorageTest {


    private ItemStorage store = ItemStorage.getInstance().getInstance();

    private CarStorage carStore = CarStorage.getInstance().getInstance();

    @Test
    public void whenAddGearBoxThenReturnId() {
        Item item = new Item();
        item.setCar(carStore.get(6));
        item.setDescription("Фольксваген 1996");
        item.setDate(new Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
        int id = store.add(item);
        Item tmpItem = new Item(id);
        assertThat(id, is(tmpItem.getItemId()));
    }


//    @Test
//    public void whenUpdateThenReturnTrue() {
//        GearBox gearBox = new GearBox(1);
//        gearBox.setGearBoxName("Роторная");
//        boolean result =  store.update(gearBox);
//        assertThat(result, is(true));
//
//    }

//    @Test
//    public void whenDeleteThenReturnTrue() {
//        GearBox gearBox = new GearBox();
//        gearBox.setGearBoxName("Автоматическая");
//        int id = store.add(gearBox);
//        GearBox tmpGearBox = new GearBox(id);
//        boolean result =  store.delete(tmpGearBox);
//        assertThat(result, is(true));
//    }
}