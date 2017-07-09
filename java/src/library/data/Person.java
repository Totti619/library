package library.data;

import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {
	private int id;
	private String name, surnames;
	
	public Person(int id, String name, String surnames) {
		super();
		this.id = id;
		this.name = name;
		this.surnames = surnames;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	@Override
	public boolean equals(Object obj) throws ClassCastException, NullPointerException {
		return (getName() + getSurnames()).equals(((Person)obj).getName() + ((Person)obj).getSurnames());
	}
	public boolean equals(Person obj) throws ClassCastException, NullPointerException {
		return (getName() + getSurnames()).equals(obj.getName() + obj.getSurnames());
	}
	public int compareTo(Person obj)  throws ClassCastException, NullPointerException {
		return (getName() + getSurnames()).compareTo(obj.getName() + obj.getSurnames());
	}
	
	public String toString() {
		return getName() + " " + getSurnames();
	}
}
