package ru.napadovskiub;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 */
public class MapTest {

    /**
     *
     */
    private final Calendar birthDate = new GregorianCalendar(1990, 8, 15);


    /**
     *
     */
    @Test
    public void whenHashCodeAndEqualsNotOverride() {
        User firstUser = new User("Иван", birthDate);

        User secondUser = new User("Иван", birthDate);

        HashMap<User, Object> mapTest = new HashMap<>();

        mapTest.put(firstUser, firstUser);
        mapTest.put(secondUser, secondUser);

        System.out.println(mapTest);

    }

    /**
     *
     */
    @Test
    public void whenHashCodeOverrideButEqualsNotOverride() {

        UserHashCode firstUser = new UserHashCode("Иван", birthDate);

        UserHashCode secondUser = new UserHashCode("Иван", birthDate);

        HashMap<User, Object> mapTest = new HashMap<>();

        mapTest.put(firstUser, firstUser);
        mapTest.put(secondUser, secondUser);
        boolean result =  firstUser.equals(secondUser);

        System.out.println(mapTest);


    }

    /**
     *
     */
    @Test
    public void whenEqualsOverrideButHashCodeNotOverride() {

        UserEquals firstUser = new UserEquals("Иван", birthDate);

        UserEquals secondUser = new UserEquals("Иван", birthDate);

        HashMap<User, Object> mapTest = new HashMap<>();

        mapTest.put(firstUser, firstUser);
        mapTest.put(secondUser, secondUser);

        System.out.println(mapTest + " " + mapTest.keySet());

    }

    /**
     *
     */
    @Test
    public void whenEqualsAndHashCodeOverride() {
        UserEqualsHashCode firstUser = new UserEqualsHashCode("Иван", birthDate);

        UserEqualsHashCode secondUser = new UserEqualsHashCode("Иван", birthDate);

        HashMap<User, Object> mapTest = new HashMap<>();

        mapTest.put(firstUser, firstUser);
        mapTest.put(secondUser, secondUser);

        System.out.println(mapTest + " " + mapTest.keySet());

    }

}
