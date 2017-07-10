package library.data;

import java.io.Serializable;

public class Subject implements Comparable<Subject>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public Subject(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Subject(String name) {
		super();
		this.id = 0;
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
		return getName().equals(((Subject)obj).getName());
	}
	public boolean equals(Subject obj) throws ClassCastException, NullPointerException {
		return getName().equals(obj.getName());
	}
	public int compareTo(Subject obj)  throws ClassCastException, NullPointerException {
		return getName().compareTo(obj.getName());
	}
	
	public String toString() {
		return getName();
	}
	
	
	
}
