package library.main;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import library.catalogue.Element;

public class Library implements Comparable<Library>, Serializable {
	
	private String name;
	private List<Element> elements;
	
	public Library(String name, List<Element> elements) {
		this.name = name;
		this.elements = new ArrayList<Element>(elements);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Element> getElements() {
		return new ArrayList<Element>(elements);
	}
	public void setElements(List<Element> elements) {
		this.elements = new ArrayList<Element>(elements);
	}
	public void addElement(Element element) {
		elements.add(element);
	}
	public void removeElement(Element element) {
		elements.remove(element);
	}

	@Override
	public boolean equals(Object obj) throws ClassCastException, NullPointerException {
		return getName().equals(((Library)obj).getName());
	}
	public boolean equals(Library obj) throws ClassCastException, NullPointerException {
		return getName().equals(obj.getName());
	}
	public int compareTo(Library obj)  throws ClassCastException, NullPointerException {
		return getName().compareTo(obj.getName());
	}
	
	public String toString() {
		List<Element> elements = getElements();
		StringBuffer res = new StringBuffer("------------------- " + getName() + " -------------------\nElements quantity: " + elements.size() + "\n\n");
		for (Iterator<Element> i = elements.iterator(); i.hasNext();) res.append(i.next().toString() + "\n\n");
		return res.toString();
	}

}
