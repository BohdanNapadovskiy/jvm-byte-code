package ru.napadovskiub.clientbank;

import ru.napadovskiub.exception.AccountNotFoundException;
import ru.napadovskiub.exception.InsufficientFundsException;
import ru.napadovskiub.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package of chapter_003 testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 15.06.2017
 */
public class Bank {

    /**
     * Map client bank with user key and Account list.
     */
    private Map<User, List<Account>> mapBank;


    /**
     *
     *
     */
    public Bank() {
        this.mapBank = new HashMap<>();
    }

    /**
     *Method add user to map.
     * @param user user for add to map.
     */
    public void addUser(User user) {
        this.mapBank.put(user, new ArrayList<>());
    }

    /**
     * Method delete user from map.
     * @param user user for delete.
     * @throws UserNotFoundException exception when user not found.
     */
    public void deleteUser(User user) throws UserNotFoundException {
        if (this.mapBank.get(user) != null) {
            this.mapBank.remove(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Method add account to user.
     * @param user user.
     * @param account account.
     * @throws UserNotFoundException exception when user not found.
     */
    public void addAccountToUser(User user, Account account) throws UserNotFoundException {
        if (this.mapBank.get(user) != null) {
            List<Account> allUserAccounts = this.mapBank.get(user);
            allUserAccounts.add(account);
        } else {
            throw new UserNotFoundException("User not found");
        }

    }

    /**
     *Method delete account from user.
     * @param user user.
     * @param account account.
     * @throws AccountNotFoundException exception when account not found.
     * @throws UserNotFoundException exception when user not found.
     */
    public void deleteAccountFromUser(User user, Account account) throws AccountNotFoundException, UserNotFoundException {
        if (this.mapBank.get(user) != null) {
            List<Account> allUserAccounts = this.mapBank.get(user);
            if (!allUserAccounts.remove(account)) {
                throw new AccountNotFoundException("Account not found");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }

    }

    /**
     *Method return all user accounts.
     * @param user user.
     * @return accounts list.
     * @throws UserNotFoundException exception when user not found.
     */
    public List<Account> getUserAccounts(User user) throws UserNotFoundException {
        if (this.mapBank.get(user) != null) {
            return this.mapBank.get(user);
        } else {
            throw new UserNotFoundException("User not found");
        }

    }

    /**
     *Method transfer money from account to account.
     * @param srcAccount Account for transfer.
     * @param dstAccount Account to transfer.
     * @param amount amount transfer.
     * @throws InsufficientFundsException exception when money not enough.
     */
    private void transferMoney(Account srcAccount, Account dstAccount, double amount) throws InsufficientFundsException {
        if (srcAccount.getValue() >= amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
        } else {
            throw new InsufficientFundsException("Insufficient funds");
        }

    }

    /**
     * Method return user account.
     * @param accounts all user accounts.
     * @param accountForSearch account for search.
     * @return user account.
     * @throws AccountNotFoundException exception when account not found.
     */
    private Account getUserAccount(List<Account> accounts, Account accountForSearch) throws AccountNotFoundException {
        int index = accounts.indexOf(accountForSearch);
        if (index < 0) {
            throw new AccountNotFoundException("Account not found");
        } else {
            return accounts.get(index);
        }

    }


    /**
     * Method transfer money from account to account.
     * @param srcUser user for transfer.
     * @param srcAccount account for transfer.
     * @param dstUser destination user.
     * @param dstAccount destination user.
     * @param amount transfer amount.
     * @return result operation.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        try {
            List<Account> accountsSrcUser = getUserAccounts(srcUser);
            Account firstAccount =  getUserAccount(accountsSrcUser, srcAccount);

            List<Account> accountsDstUser = getUserAccounts(dstUser);
            Account secondAccount =  getUserAccount(accountsDstUser, dstAccount);

            transferMoney(firstAccount, secondAccount, amount);

            result = true;

        } catch (AccountNotFoundException | InsufficientFundsException | UserNotFoundException exception) {
            exception.getMessage();
        }



        return result;
    }


    /**
     * Returns map.
     *
     * @return HashMap.
     */
    public Map<User, List<Account>> getMap() {
        return mapBank;
    }
}
