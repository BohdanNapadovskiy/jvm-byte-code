package ru.napadovskiu.storage;

import org.junit.Test;
import ru.napadovskiu.entities.Engine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EngineStorageTest {

    private EngineStorage store = EngineStorage.getInstance();

    @Test
    public void whenAddEngineThenReturnId() {
        Engine engine = new Engine();
        engine.setEngineName("Бензиновый");
        int idEngine = store.add(engine);
        Engine tmpEngine = new Engine(idEngine);
        assertThat(idEngine, is(tmpEngine.getEngineId()));
    }


    @Test
    public void whenUpdateThenReturnTrue() {
        Engine engine = new Engine(40);
        engine.setEngineName("Дизельный");
        boolean result =  store.update(engine);
        assertThat(result, is(true));

    }

    @Test
    public void whenDeleteThenReturnTrue() {
        Engine engine = new Engine();
        engine.setEngineName("Газовый");
        int idEngine = store.add(engine);
        Engine tmpEngine = new Engine(idEngine);
        boolean result =  store.delete(tmpEngine);
        assertThat(result, is(true));
    }

}