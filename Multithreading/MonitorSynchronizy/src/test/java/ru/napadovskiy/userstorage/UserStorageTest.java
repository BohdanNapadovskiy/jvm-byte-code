package ru.napadovskiy.userstorage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class UserStorageTest {

    /**
     *
     */
    private UserStorage userStorage  = new UserStorage();

    /**
     *
     */
    private final User firstUser = new User(1, 100);

    /**
     *
     */
    private final User secondUser = new User(2, 150);

    /**
     *
     */
    @Test
    public void whenAddThenReturnTrue() {
        boolean result = this.userStorage.add(firstUser);

        assertThat(result, is(true));

    }

    /**
     *
     */
    @Test
    public void whenDeleteSuccessThenReturnSize() {

        this.userStorage.add(firstUser);
        this.userStorage.add(secondUser);
        boolean result = this.userStorage.delete(firstUser);

        assertThat(result, is(true));

    }

    /**
     *
     */
    @Test
    public void whenUpdateSuccessThenReturnNewAmount()  {
        final int checkSum = 150;
        final int amountForTransfer = 50;
        this.userStorage.add(firstUser);
        this.userStorage.add(secondUser);
        this.userStorage.update(firstUser, firstUser.getAmount() + amountForTransfer);
        User checkUser =  userStorage.getById(firstUser.getIdUser());

        assertThat(checkUser.getAmount(), is(checkSum));
    }

    /**
     *
     * @throws Exception exception.
     */
    @Test
    public void whenTransferSuccessThenReturnNewAmount() throws Exception {
        final int checkSum = 150;
        final int amountForTransfer = 50;
        this.userStorage.add(firstUser);
        this.userStorage.add(secondUser);
        this.userStorage.transfer(secondUser.getIdUser(), firstUser.getIdUser(), amountForTransfer);
        User checkUser =  userStorage.getById(firstUser.getIdUser());

        assertThat(checkUser.getAmount(), is(checkSum));


    }

}