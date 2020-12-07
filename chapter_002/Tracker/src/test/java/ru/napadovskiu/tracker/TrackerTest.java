package ru.napadovskiu.tracker;

import ru.napadovskiu.items.Item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;



/**
 * Created by napadovskiy on 13.09.2016.
 *@author napadovskiy
 *@since 13.09.2016
 *@version 1
 */
public class TrackerTest {

	/**
	 *
	 */
	@Test
	public void whenAddItem() {

		Tracker testTracker = new Tracker();
		Item firstItem 	= new Item("firstItem", "descriptionFirstItem");
		testTracker.addNewItem(firstItem);
		assertThat(testTracker.findItemById(firstItem.getId()), is(firstItem));
	}

	/**
	 *
	 */
	@Test
	public void whenDeleteItem() {
		Tracker testTracker = new Tracker();
		boolean isDeleteItem;
		Item firstItem 	= new Item("firstItem", "descriptionFirstItem");
		testTracker.addNewItem(firstItem);
		isDeleteItem = testTracker.deleteItem(firstItem);
		assertThat(isDeleteItem, is(true));
	}

	/**
	 *
	 */
	@Test
	public void whenFindItem() {
		Tracker testTracker = new Tracker();
		Item firstItem 	= new Item("firstItem", "descriptionFirstItem");
		testTracker.addNewItem(firstItem);
		Item secondItem = new Item("secondItem", "descriptionSecondItem");
		testTracker.addNewItem(secondItem);
		Item[] testArray = new Item[] {firstItem};
		assertThat(testTracker.findItemById(secondItem.getId()), is(secondItem));
	}

	/**
	 *
	 */
	@Test
	public void whenEditItem() {
		Tracker testTracker = new Tracker();
		Item firstItem 	= new Item("firstItem", "descriptionFirstItem");
		testTracker.addNewItem(firstItem);
		Item editItem = new Item("secondItem", "descriptionSecondItem");
		editItem.setId(firstItem.getId());
		testTracker.editItem(editItem);
		assertThat(firstItem.getName(), is(editItem.getName()));
	}

}