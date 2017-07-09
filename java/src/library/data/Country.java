package library.data;

import java.io.Serializable;

public class Country implements Comparable<Company>, Serializable {
	private int id;
	private String name;
	
	public Country(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	@Override
	public boolean equals(Object obj) throws ClassCastException, NullPointerException {
		return getName().equals(((Company)obj).getName());
	}
	public boolean equals(Company obj) throws ClassCastException, NullPointerException {
		return getName().equals(obj.getName());
	}
	public int compareTo(Company obj)  throws ClassCastException, NullPointerException {
		return getName().compareTo(obj.getName());
	}
	
	public String toString() {
		return "ID: " + getId() + "\nCOUNTRY: " + getName();
	}
}
