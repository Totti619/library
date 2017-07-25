package library.catalogue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.data.Company;
import library.data.Country;
import library.data.Person;
import library.data.Subject;

public class Magazine extends Element {
	
	private static final long serialVersionUID = 1L;
	private String isbn;
	private List<Person> founders;
	private Company publishingCompany;
	
	public Magazine(int id, String title, int year, List<Subject> subjects, int stock, Country country, String isbn, List<Person> founders,
			Company publishingCompany) {
		super(id, title, year, subjects, stock, country);
		this.isbn = isbn;
		this.founders = new ArrayList<Person>(founders);
		this.publishingCompany = publishingCompany;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<Person> getFounders() {
		return new ArrayList<Person>(founders);
	}
	public void setFounders(List<Person> founders) {
		this.founders = new ArrayList<Person>(founders);
	}
	public Company getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(Company publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer(super.toString());
		res.append("\nISBN: " + getIsbn());
		res.append("\nPUBLISHING COMPANY: " + getPublishingCompany().getName());
		res.append("\nFOUNDERS: ");
		for (Iterator<Person> i = getFounders().iterator(); i.hasNext();) {
			Person founder = i.next();
			if (i.hasNext()) res.append(founder + ", ");
			else res.append(founder);
		}
		return res.toString();
	}
	
}
