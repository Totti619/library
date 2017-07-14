package library.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Company implements Comparable<Company>, Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<Industry> industries;
	private int numberEmployees;

	public Company(int id, String name, List<Industry> industries, int numberEmployees) {
		super();
		this.id = id;
		this.name = name;
		this.industries = new ArrayList<Industry>(industries);
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
	public List<Industry> getIndustries() {
		return new ArrayList<Industry>(industries);
	}
	public void setIndustries(List<Industry> industries) {
		this.industries = new ArrayList<Industry>(industries);
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
		StringBuffer res = new StringBuffer("ID: " + getId() + "\nNAME: " + getName() + "\nNUM. EMPLOYEES: " + getNumberEmployees() + "\nINDUSTRIES: ");
		for (Iterator<Industry> i = getIndustries().iterator(); i.hasNext();) {
			Industry industry = i.next();
			if (i.hasNext()) res.append(industry + ", "); else res.append(industry);
		}
		return res.toString();
	}
}
