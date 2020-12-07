package ru.napadovskiu.tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import  ru.napadovskiu.items.Item;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by napadovskiy on 13.09.2016.
 *@author napadovskiy
 *@since 13.09.2016
 *@version 1
 */
public class StartUITest {

    /**
     * User action.
      */
    private StartUI userAction;

    /**
     * tracker.
     */
    private Tracker tracker = new Tracker();

    /**
     * Method init main menu.
     * @param params params for init.
     */
    private void init(String[] params) {
        Input input = new StubInput(params);
        this.userAction = new StartUI(input, this.tracker);
        userAction.init();
    }

    /**
     *Method add item to tracker.
     */
    @Test
    public void whenAddNewItemThenTrackerAddItem() {
        Item tmpItem = new Item("namefirstItem", "descfirstItem");
        String[] params = new String[] {"1", tmpItem.getName(), tmpItem.getDescription(), "8"};
        this.init(params);
        ArrayList<Item> resultList = this.tracker.findItemByName("namefirstItem");
        assertThat(resultList.get(0).getName(), is(tmpItem.getName()));
   }

    /**
     *Method find item in Array list.
     */
   @Test
    public void whenFindItemByName() {
        String[] firstParams = new String[] {"1", "namefirstItem", "descfirstItem", "1", "namefirstItem1", "descfirstItem1", "1", "namesecondItem", "DescsecondItem", "8"};
        this.init(firstParams);
        String[] secondParams = new String[] {"3", "namefirstItem", "8"};
        this.init(secondParams);
        ArrayList<Item> resultList = this.tracker.findItemByName("namefirstItem");
        assertThat(resultList.get(0).getName(), is("namefirstItem"));
    }

    /**
     *Method find item by id.
     */
    @Test
    public void whenFindById() {
        String[] firstParams = new String[] {"1", "namefirstItem", "descfirstItem", "1", "nameSecondItem", "descSecondItem", "8"};
        this.init(firstParams);
        String[] secondParams = new String[] {"4", this.tracker.getAllItem().get(0).getId(), "8"};
        this.init(secondParams);
        assertThat(this.tracker.findItemById(this.tracker.getAllItem().get(0).getId()).getName(), is("namefirstItem"));

    }

    /**
     *Method edit item.
     */
    @Test
    public void whenEditItem() {
        String[] firstParams = new String[] {"1", "namefirstItem", "descfirstItem", "1", "nameSecondItem", "descSecondItem", "8"};
        this.init(firstParams);
        ArrayList<Item> resultArray = this.tracker.findItemByName("namefirstItem");
        Item resultItem = resultArray.get(0);
        String[] secondParams = new String[] {"5", resultItem.getId(), "nameEditItem", "descEditItem", "8"};
        this.init(secondParams);
        assertThat(this.tracker.findItemById(this.tracker.getAllItem().get(0).getId()).getName(), is("nameEditItem"));
    }

    /**
     *Method delete item.
     */
    @Test
    public void whenDeleteItem() {
        String[] firstParams = new String[] {"1", "namefirstItem", "descfirstItem", "1", "nameSecondItem", "descSecondItem", "8"};
        this.init(firstParams);
        ArrayList<Item> resultArray = this.tracker.findItemByName("namefirstItem");
        Item resultItem = resultArray.get(0);
        String[] secondParams = new String[] {"6", resultItem.getId(), "8"};
        final int sizeRezultArray = 1;
        this.init(secondParams);

        assertThat(this.tracker.getAllItem().size(), is(sizeRezultArray));


    }

}
