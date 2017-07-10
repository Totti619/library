package library.catalogue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.data.Country;
import library.data.Subject;

public class Element implements Comparable<Element>, Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private short year;
	private List<Subject> subjects;
	private int stock;
	private Country country;

	// TODO method addStock(int arg), removeStock(int arg)
	
	public Element(int id, String title, int year, List<Subject> subjects, int stock, Country country) {
		super();
		this.id = id;
		this.title = title;
		this.year = (short)year;
		this.subjects = subjects;
		this.stock = stock;
		this.country = country;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public short getYear() {
		return year;
	}
	public void setYear(short year) {
		this.year = year;
	}
	public List<Subject> getSubjects() {
		return new ArrayList<Subject>(subjects);
	}
	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = new ArrayList<Subject>(subjects);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void addStock(int stock) {
		this.stock = this.stock + stock;
	}
	public void removeStock(int stock) {
		this.stock = this.stock - stock;
		if (getStock() < 0) this.stock = 0;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object obj) throws ClassCastException, NullPointerException {
		return getTitle().equals(((Element)obj).getTitle());
	}
	public boolean equals(Element obj) throws ClassCastException, NullPointerException {
		return getTitle().equals(obj.getTitle());
	}
	public int compareTo(Element obj)  throws ClassCastException, NullPointerException {
		return getId() - obj.getId();
	}
	
	public String toString() {
		List<Subject> subjects = getSubjects();
		StringBuffer res = new StringBuffer("ID: " + getId() + "\nTYPE: " + getClass().getSimpleName() + "\nTITLE: " + getTitle() + "\nYEAR: " + getYear() + "\nSTOCK: " + getStock() + "\nCOUNTRY: " + getCountry().getName() + "\nSUBJECTS: ");
		for (Iterator<Subject> i = subjects.iterator(); i.hasNext();) {
			Subject subject = i.next();
			if (i.hasNext()) res.append(subject + ", "); else res.append(subject);
		}
		return res.toString();
	}

}
