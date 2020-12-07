package ru.napadovskiu.storage;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import org.springframework.context.ApplicationContext;
import static org.junit.Assert.assertEquals;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.napadovskiu.models.User;


public class StorageTest {

    private User firstUser;

    private User secondUser;

    @Before
    public void initTest() {
        this.firstUser = new User();
        this.firstUser.setUserId(1);
        this.firstUser.setUserName("first");

        this.secondUser = new User();
        this.secondUser.setUserId(2);
        this.secondUser.setUserName("second");

    }


    @Test
    public void whenAddUserMemoryStorage() {
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-context.xml");
        Storage storage = context.getBean(MemoryStorage.class);

        storage.add(this.firstUser);
        storage.add(this.secondUser);

        Integer userId = storage.get(secondUser.getUserId()).getUserId();

        assertEquals(userId, this.secondUser.getUserId());
    }


    @Test
    public void whenAddUserJdbcStorage() {
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-context.xml");
        Storage storage = context.getBean(JdbcStorage.class);

        storage.add(this.firstUser);
        storage.add(this.secondUser);

        Integer userId = storage.get(secondUser.getUserId()).getUserId();

        assertEquals(userId, this.secondUser.getUserId());
    }


}
