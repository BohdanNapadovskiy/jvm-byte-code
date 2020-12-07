package ru.napadovskiub.clientbank;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.napadovskiub.exception.AccountNotFoundException;
import ru.napadovskiub.exception.InsufficientFundsException;
import ru.napadovskiub.exception.UserNotFoundException;

import static org.hamcrest.core.Is.is;
import java.util.List;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Package of chapter_003 testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 15.06.2017
 */
public class BankTest {

    /**
     *
     */
    private User firstUser;

    /**
     *
     */
    private Bank clientBank;

    /**
     *
     */
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     *
     */
    @Before
    public void initMethod() {
        firstUser = new User("Petrovich", "AAS1131");
        clientBank = new Bank();
    }

    /**
     * Method test add user to map.
     */
    @Test
    public void whenAddUserThenReturnTrue() {
        clientBank.addUser(firstUser);

        assertTrue(clientBank.getMap().containsKey(firstUser));

    }

    /**
     * Method add account to user.
     * @throws UserNotFoundException exception.
     */
    @Test
    public void whenAddAccountToMapThenReturnArray() throws UserNotFoundException {
        final int requisites = 1235656;
        Account account = new Account(0.0, requisites);

        clientBank.addUser(firstUser);
        clientBank.addAccountToUser(firstUser, account);

        List resultList = clientBank.getUserAccounts(firstUser);

        assertThat(resultList.toArray()[0], is(account));
    }

    /**
     * Method test add account to user.
     * @throws UserNotFoundException exception.
     */
    @Test(expected = UserNotFoundException.class)
    public void whenAddAccountAndNotFoundUserTheReturnException() throws UserNotFoundException {
        final int requisites = 1235656;
        Account account = new Account(0.0, requisites);

        clientBank.addAccountToUser(firstUser, account);
    }

    /**
     * Method test delete user.
     * @throws UserNotFoundException exception.
     */
    @Test
    public void whenDeleteUserThenReturnEmptyMap()  throws UserNotFoundException {

        User secondUser = new User("Pavlovich", "AB151332");

        clientBank.addUser(firstUser);
        clientBank.addUser(secondUser);

        clientBank.deleteUser(firstUser);

        assertThat(clientBank.getMap().containsKey(firstUser), is(false));

    }

    /**
     * Method test delete user.
     * @throws UserNotFoundException exception.
     */
    @Test(expected = UserNotFoundException.class)
    public void whenDeleteUserAndUserNotFoundThenReturnException() throws UserNotFoundException {
        User secondUser = new User("Pavlovich", "AB151332");

        clientBank.addUser(firstUser);
        clientBank.deleteUser(secondUser);

    }

    /**
     * Method test delete account.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     */
    @Test
    public void whenDeleteAccountSuccessfullyThenReturnTrue() throws UserNotFoundException, AccountNotFoundException {
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;

        Account firstAccount = new Account(0.00, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addAccountToUser(firstUser, firstAccount);
        clientBank.addAccountToUser(firstUser, secondAccount);

        clientBank.deleteAccountFromUser(firstUser, firstAccount);

        List<Account> accountsList = clientBank.getUserAccounts(firstUser);
        assertThat(accountsList.get(0), is(accountsList.get(0)));

    }

    /**
     * Method test delete account.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     */
    @Test(expected = UserNotFoundException.class)
    public void whenDeleteAccountAndUserNotFoundThenReturnExceptionUserNotFound() throws UserNotFoundException, AccountNotFoundException {
        User secondUser = new User("Pavlovich", "AB151332");
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;

        Account firstAccount = new Account(0.00, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addAccountToUser(firstUser, firstAccount);
        clientBank.addAccountToUser(firstUser, secondAccount);

        clientBank.deleteAccountFromUser(secondUser, firstAccount);

    }

    /**
     * Method test delete account.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     */
    @Test(expected = AccountNotFoundException.class)
    public void whenDeleteAccountAndAccountNotFoundThenReturnExceptionAccountNotFound() throws UserNotFoundException, AccountNotFoundException {
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;

        Account firstAccount = new Account(0.00, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addAccountToUser(firstUser, firstAccount);

        clientBank.deleteAccountFromUser(firstUser, secondAccount);

    }

    /**
     * Method test get users account.
     * @throws UserNotFoundException exception.
     */
    @Test
    public void whenGetUserAccountThenReturnArrayList() throws UserNotFoundException {
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;

        Account firstAccount = new Account(0.00, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addAccountToUser(firstUser, firstAccount);
        clientBank.addAccountToUser(firstUser, secondAccount);

        List accountList = clientBank.getUserAccounts(firstUser);

        assertThat(accountList.size(), is(2));

    }

    /**
     * Method test get users account.
     * @throws UserNotFoundException exception.
     */
    @Test(expected = UserNotFoundException.class)
    public void whenGetUserAccountAndUserNotFoundThenReturnException() throws UserNotFoundException {
        final int firstRequisites = 1235656;
        Account firstAccount = new Account(0.00, firstRequisites);

        clientBank.addAccountToUser(firstUser, firstAccount);

    }

    /**
     * Method test money transfer.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     * @throws InsufficientFundsException exception.
     */
    @Test
    public void whenTransferMoneyButUserNotFoundThenReturnFalse() throws UserNotFoundException, AccountNotFoundException, InsufficientFundsException {
        boolean expected = false;
        User secondUser = new User("Pavlovich", "AB151332");
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;
        final double firstValue = 100.00;
        final double amountForTransfer = 50.00;

        Account firstAccount = new Account(firstValue, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addAccountToUser(firstUser, firstAccount);


        boolean result = clientBank.transferMoney(firstUser, firstAccount, secondUser, secondAccount, amountForTransfer);

        assertThat(result, is(expected));

    }

    /**
     * Method test money transfer.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     * @throws InsufficientFundsException exception.
     */
    @Test
    public void whenTransferMoneyButAccountNotFoundThenReturnFalse() throws UserNotFoundException, AccountNotFoundException, InsufficientFundsException {
        boolean expected = false;
        User secondUser = new User("Pavlovich", "AB151332");
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;
        final double firstValue = 100.00;
        final double amountForTransfer = 50.00;


        Account firstAccount = new Account(firstValue, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addUser(secondUser);

        clientBank.addAccountToUser(firstUser, firstAccount);

        boolean result = clientBank.transferMoney(firstUser, firstAccount, secondUser, secondAccount, amountForTransfer);

        assertThat(result, is(expected));

    }

    /**
     * Method test money transfer.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     * @throws InsufficientFundsException exception.
     */
    @Test
    public void whenTransferMoneyButNotEnoughMoneyThenReturnFalse() throws UserNotFoundException, AccountNotFoundException, InsufficientFundsException {
        boolean expected = false;
        User secondUser = new User("Pavlovich", "AB151332");
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;
        final double firstValue = 100.00;
        final double amountForTransfer = 200.00;

        Account firstAccount = new Account(firstValue, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addUser(secondUser);

        clientBank.addAccountToUser(firstUser, firstAccount);
        clientBank.addAccountToUser(secondUser, secondAccount);

        boolean result = clientBank.transferMoney(firstUser, firstAccount, secondUser, secondAccount, amountForTransfer);

        assertThat(result, is(expected));
    }

    /**
     * Method test money transfer.
     * @throws UserNotFoundException exception.
     * @throws AccountNotFoundException exception.
     * @throws InsufficientFundsException exception.
     */
    @Test
    public void whenTransferMoneyAndAllIsWellThenReturnTrue() throws UserNotFoundException, AccountNotFoundException, InsufficientFundsException {
        boolean expected = true;
        User secondUser = new User("Pavlovich", "AB151332");
        final int firstRequisites = 1235656;
        final int secondRequisites = 125476641;
        final double firstValue = 100.00;
        final double amountForTransfer = 50.00;


        Account firstAccount = new Account(firstValue, firstRequisites);
        Account secondAccount = new Account(0.00, secondRequisites);

        clientBank.addUser(firstUser);
        clientBank.addUser(secondUser);

        clientBank.addAccountToUser(firstUser, firstAccount);
        clientBank.addAccountToUser(secondUser, secondAccount);

        boolean result = clientBank.transferMoney(firstUser, firstAccount, secondUser, secondAccount, amountForTransfer);

        assertThat(result, is(expected));

    }

}