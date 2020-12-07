package ru.napadovskiu.storage;

import org.junit.Test;
import ru.napadovskiu.entities.Transmission;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TransStorageTest {

    private TransStorage store = TransStorage.getInstance();

    @Test
    public void whenAddEngineThenReturnId() {
        Transmission trans = new Transmission();
        trans.setTransName("Механическая");
        int idTrans = store.add(trans);
        Transmission tmpTrans = new Transmission(idTrans);
        assertThat(idTrans, is(tmpTrans.getTransId()));
    }


    @Test
    public void whenUpdateThenReturnTrue() {
        Transmission trans = new Transmission(1);
        trans.setTransName("Электрическая");
        boolean result =  store.update(trans);
        assertThat(result, is(true));

    }

    @Test
    public void whenDeleteThenReturnTrue() {
        Transmission trans = new Transmission();
        trans.setTransName("Комбинированная");
        int idTrans = store.add(trans);
        Transmission tmpTrans = new Transmission(idTrans);
        boolean result =  store.delete(tmpTrans);
        assertThat(result, is(true));
    }

}