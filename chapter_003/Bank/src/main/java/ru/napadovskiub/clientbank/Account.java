package ru.napadovskiub.clientbank;

/**
 * Package of chapter_003 testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 15.06.2017
 */
public class Account {

    /**
     * value of money.
     */
    private double value;

    /**
     * Account requisites.
     */
    private int requisites;

    /**
     * Constructor for Account class.
     * @param value Account value.
     * @param requisites Account requisites.
     */
    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Method return account requisites.
     * @return account requisites.
     */
    public int getRequisites() {
        return this.requisites;
    }


    /**
     * Method return value.
     * @return value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     *Method set value account.
     * @param value account value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *Method set requisites to account.
     * @param requisites account requisites.
     */
    public void setRequisites(int requisites) {
        this.requisites = requisites;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (Double.compare(account.value, value) != 0) {
            return false;
        }
        return requisites == account.requisites;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        final int var = 32;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> var));
        result = (var - 1) * result + requisites;
        return result;
    }

    @Override
    public String toString() {
        return "Account{" + "value=" + value + ", requisites=" + requisites + '}';
    }
}

