package ru.napadovskiu.filemanager.menufilemanager;

import java.io.IOException;

public interface UserAction {

	String key();

	void execute(String string) throws IOException;
	
	String info();

}