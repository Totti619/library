package library.data;

import java.io.Serializable;

public class Company implements Comparable<Company>, Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name, industry;
	private int numberEmployees;
	
	public Company(int id, String name, String industry, int numberEmployees) {
		super();
		this.id = id;
		this.name = name;
		this.industry = industry;
		this.numberEmployees = numberEmployees;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public int getNumberEmployees() {
		return numberEmployees;
	}
	public void setNumberEmployees(int numberEmployees) {
		this.numberEmployees = numberEmployees;
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
		return "ID: " + getId() + "\nNAME: " + getName() + "\nINDUSTRY: " + getIndustry() + "\nNUM. EMPLOYEES: " + getNumberEmployees();
	}
}
