package ru.napadovskiyb;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 *
 */
public class MySimpleTreeTest {

    /**
     *
     */
    private MySimpleTree<String>  tree = new MySimpleTree<>();

    /**
     *
     */
    @Test
    public void whenAddElementSaucesThenReturnSize() {

        this.tree.add("1", "1");
        this.tree.add("1", "2");
        final int checkResult = 3;

        assertThat(this.tree.getSize(), is(checkResult));

    }

    /**
     *
     */
    @Test
    public void whenAddElementNotSaucesThenReturnSize() {

        this.tree.add("1", "1");
        this.tree.add("2", "2");

        int checkResult = 1;

        assertThat(this.tree.getListOfChild().size(), is(1));
    }

    /**
     *
     */
    @Test
    public void whenTreeIsBinaryThanReturnTrue() {

        this.tree.add("1", "2");
        this.tree.add("1", "4");
        this.tree.add("3", "5");

        assertThat(this.tree.isBinary(), is(true));

    }

    /**
     *
     */
    @Test
    public void whenAddSuccessfulThenReturnSize() {
        this.tree.add("24");
        this.tree.add("5");
        this.tree.add("26");

        final int checkResult = 3;

        assertThat(this.tree.getSize(), is(checkResult));

    }

}