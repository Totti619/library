package library.catalogue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.data.Company;
import library.data.Country;
import library.data.Person;
import library.data.Subject;

public class Book extends Element {

	private static final long serialVersionUID = 1L;
	private String isbn;
	private List<Person> authors;
	private Company publishingCompany;
	private byte editionNumber;
	
	public Book(int id, String title, int year, List<Subject> subjects, int stock, Country country, String isbn, List<Person> authors,
			Company publishingCompany, int editionNumber) {
		super(id, title, year, subjects, stock, country);
		this.isbn = isbn;
		this.authors = new ArrayList<Person>(authors);
		this.publishingCompany = publishingCompany;
		this.editionNumber = (byte)editionNumber;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<Person> getAuthors() {
		return new ArrayList<Person>(authors);
	}
	public void setAuthor(Person author) {
		this.authors = new ArrayList<Person>(authors);
	}
	public Company getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(Company publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	public byte getEditionNumber() {
		return editionNumber;
	}
	public void setEditionNumber(byte editionNumber) {
		this.editionNumber = editionNumber;
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer(super.toString());
		res.append("\nISBN: " + getIsbn());
		res.append("\nPUBLISHING COMPANY: " + getPublishingCompany().getName());
		res.append("\nEDITION NUMBER: " + getEditionNumber());
		for (Iterator<Person> i = getAuthors().iterator(); i.hasNext();) {
			Person author = i.next();
			if (i.hasNext()) res.append(author.getName() + ", ");
			else res.append(author.getName());
		}
		return res.toString();
	}
}
