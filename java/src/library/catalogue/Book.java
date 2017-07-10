package library.catalogue;

import java.util.List;

import library.data.Company;
import library.data.Country;
import library.data.Person;
import library.data.Subject;

public class Book extends Element {

	private static final long serialVersionUID = 1L;
	private String isbn;
	private Person author;
	private Company publishingCompany;
	private byte editionNumber;
	
	public Book(int id, String title, int year, List<Subject> subjects, int stock, Country country, String isbn, Person author,
			Company publishingCompany, int editionNumber) {
		super(id, title, year, subjects, stock, country);
		this.isbn = isbn;
		this.author = author;
		this.publishingCompany = publishingCompany;
		this.editionNumber = (byte)editionNumber;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Person getAuthor() {
		return author;
	}
	public void setAuthor(Person author) {
		this.author = author;
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
		res.append("\nAUTHOR: " + getAuthor());
		res.append("\nPUBLISHING COMPANY: " + getPublishingCompany().getName());
		res.append("\nEDITION NUMBER: " + getEditionNumber());
		return res.toString();
	}
}
