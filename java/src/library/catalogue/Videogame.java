package library.catalogue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.data.Company;
import library.data.Country;
import library.data.Person;
import library.data.Subject;

public class Videogame extends Element {

	private static final long serialVersionUID = 1L;
	private List<Company> developingCompanies;
	private List<Company> publishingCompanies;
	private List<Person> directors;
	private List<Person> producers;
	private List<Person> designers;
	private List<Person> programmers;
	
	public Videogame(int id, String title, int year, List<Subject> subjects, int stock, Country country,
			List<Company> developingCompanies, List<Company> publishingCompanies,
			List<Person> directors, List<Person> producers,
			List<Person> designers, List<Person> programmers) {
		super(id, title, year, subjects, stock, country);
		this.developingCompanies = new ArrayList<Company>(developingCompanies);
		this.publishingCompanies = new ArrayList<Company>(publishingCompanies);
		this.directors = new ArrayList<Person>(directors);
		this.producers = new ArrayList<Person>(producers);
		this.designers = new ArrayList<Person>(designers);
		this.programmers = new ArrayList<Person>(programmers);
	}

	public List<Company> getDevelopingCompanies() {
		return new ArrayList<Company>(developingCompanies);
	}
	public void setDevelopingCompanies(List<Company> developingCompanies) {
		this.developingCompanies = new ArrayList<Company>(developingCompanies);
	}
	public List<Company> getPublishingCompanies() {
		return new ArrayList<Company>(publishingCompanies);
	}
	public void setPublishingCompanies(List<Company> publishingCompanies) {
		this.publishingCompanies = new ArrayList<Company>(publishingCompanies);
	}
	public List<Person> getDirectors() {
		return new ArrayList<Person>(directors);
	}
	public void setDirectors(List<Person> directors) {
		this.directors = new ArrayList<Person>(directors);
	}
	public List<Person> getProducers() {
		return new ArrayList<Person>(producers);
	}
	public void setProducers(List<Person> producers) {
		this.producers = new ArrayList<Person>(producers);
	}
	public List<Person> getDesigners() {
		return new ArrayList<Person>(designers);
	}
	public void setDesigners(List<Person> designers) {
		this.designers = new ArrayList<Person>(designers);
	}
	public List<Person> getProgrammers() {
		return new ArrayList<Person>(programmers);
	}
	public void setProgrammers(List<Person> programmers) {
		this.programmers = new ArrayList<Person>(programmers);
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer(super.toString());
		res.append("\nDEVELOPERS: ");
		List<Company> developingCompanies = getDevelopingCompanies();
		for (Iterator<Company> i = developingCompanies.iterator(); i.hasNext();) {
			Company company = i.next();
			if (i.hasNext()) res.append(company.getName() + ", "); else res.append(company.getName());
		}
		res.append("\nPUBLISHERS: ");
		List<Company> publishingCompanies = getPublishingCompanies();
		for (Iterator<Company> i = publishingCompanies.iterator(); i.hasNext();) {
			Company company = i.next();
			if (i.hasNext()) res.append(company.getName() + ", "); else res.append(company.getName());
		}
		res.append("\nDIRECTORS: ");
		List<Person> directors = getDirectors();
		for (Iterator<Person> i = directors.iterator(); i.hasNext();) {
			Person person = i.next();
			if (i.hasNext()) res.append(person + ", "); else res.append(person);
		}
		res.append("\nPRODUCERS: ");
		List<Person> producers = getProducers();
		for (Iterator<Person> i = producers.iterator(); i.hasNext();) {
			Person person = i.next();
			if (i.hasNext()) res.append(person + ", "); else res.append(person);
		}
		res.append("\nDESIGNERS: ");
		List<Person> designers = getDesigners();
		for (Iterator<Person> i = designers.iterator(); i.hasNext();) {
			Person person = i.next();
			if (i.hasNext()) res.append(person + ", "); else res.append(person);
		}
		res.append("\nPROGRAMMERS: ");
		List<Person> programmers = getProgrammers();
		for (Iterator<Person> i = programmers.iterator(); i.hasNext();) {
			Person person = i.next();
			if (i.hasNext()) res.append(person + ", "); else res.append(person);
		}
		return res.toString();
	}
}
