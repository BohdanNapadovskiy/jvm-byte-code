package ru.napadovskiu.storage;

import org.junit.Test;
import ru.napadovskiu.entities.GearBox;
import ru.napadovskiu.entities.Item;
import ru.napadovskiu.entities.User;

import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    private UserStorage store = UserStorage.getInstance().getInstance();

    private ItemStorage itemStore = ItemStorage.getInstance().getInstance();


    @Test
    public void whenAddGearBoxThenReturnId() {
//        User user = new User();
//        user.setUserName("Admin");
//        user.setUserPassword("Admin");
//        int id = store.add(user);
//        User tmpUser = new User(id);
//
//        Set<Item> itemSet = itemStore.getAllByUser(id);
//        user.setItemSet(itemSet);
//        assertThat(id, is(tmpUser.getUserId()));
    }


//    @Test
//    public void whenUpdateThenReturnTrue() {
//        GearBox gearBox = new GearBox(1);
//        gearBox.setGearBoxName("Роторная");
//        boolean result =  store.update(gearBox);
//        assertThat(result, is(true));
//
//    }
//
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