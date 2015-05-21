package logic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DataContainer<T> {
	private final List<T> elements;
	private final Date date;

	public DataContainer(Date date) {
		this.date = date;
		this.elements = new LinkedList<>();
	}

	public void addElement(T element) {
		elements.add(element);
	}

	public List<T> getElements() {
		return elements;
	}

	public Date getDate() {
		return date;
	}
}
