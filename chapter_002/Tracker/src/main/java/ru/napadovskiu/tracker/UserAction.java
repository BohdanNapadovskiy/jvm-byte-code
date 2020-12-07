package ru.napadovskiu.tracker;

/**
 *@author napadovskiy
 *@since 13.09.2016
 *@version 1
 */
public interface UserAction {

	/**
	 *
	 * @return key.
	 */
	int key();

	/**
	 *
	 * @param input input.
	 * @param tracker tracker.
	 */
	void execute(Input input, Tracker tracker);

	/**
	 *
	 * @return info.
	 */
	String info();
}