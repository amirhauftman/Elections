package model;

import java.util.ArrayList;

public class Set<T> {

	// the array list is of type T which implements Comparable
	protected ArrayList<T> list;

	// Constructor sets the array
	public Set() {
		list = new ArrayList<T>();
	}

	// Add an object to the list
	public Boolean add(T obj) {
		for (T t : list) {
			if (t.equals(obj))
				return false;
		}
		list.add(obj);
		return true;
	}

	// Get an object from the list by index
	public T get(int index) {
		return list.get(index);
	}

	// Remove an item from the list
	public Boolean remove(T obj) {
		return list.remove(obj);
	}

	// Remove an index from the list
	public T remove(int index) {
		return list.remove(index);
	}

	// Get the size of the list
	public int getSize() {
		return list.size();
	}

	// find an object in the list by using compareTo
	public T find(T obj) {
		for (T t : list) {
			if (t.equals(obj))
				return t;
		}
		return null;
	}

	public String toString() {
		String s = "";
		for (T t : list) {
			s += t.toString() + "\n";
		}
		return s;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (!(obj instanceof Set))
			return false;
		if (((Set) obj).getSize() != getSize())
			return false;
		return true;
	}

}
