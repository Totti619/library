package library.data;

import java.io.Serializable;

public class Industry implements Comparable<Industry>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public Industry(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Industry(String name) {
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
		return getName().equals(((Industry)obj).getName());
	}
	public boolean equals(Industry obj) throws ClassCastException, NullPointerException {
		return getName().equals(obj.getName());
	}
	public int compareTo(Industry obj)  throws ClassCastException, NullPointerException {
		return getName().compareTo(obj.getName());
	}
	
	public String toString() {
		return getName();
	}
}
