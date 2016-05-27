package com.fsm.parserfactory;

public interface Parser<T> {
	
	T getFirstElement();

	T getLastElement();

	T getElementByIndex(int index);

	T getElementByName(String name);

}
