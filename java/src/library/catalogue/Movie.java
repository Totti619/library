package library.catalogue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.data.Country;
import library.data.Person;
import library.data.Subject;

public class Movie extends Element {

	private static final long serialVersionUID = 1L;
	private Person director;
	private List<Person> producers;
	private List<Person> scriptwriters;
	private List<Person> protagonists;
	private int duration;
	public Movie(int id, String title, int year, List<Subject> subjects, int stock, Country country, Person director,
			List<Person> producers, List<Person> scriptwriters,
			List<Person> protagonists, int duration) {
		super(id, title, year, subjects, stock, country);
		this.director = director;
		this.producers = producers;
		this.scriptwriters = scriptwriters;
		this.protagonists = protagonists;
		this.duration = duration;
	}
	
	public Person getDirector() {
		return director;
	}
	public void setDirector(Person director) {
		this.director = director;
	}
	public List<Person> getProducers() {
		return new ArrayList<Person>(producers);
	}
	public void setProducers(List<Person> producers) {
		this.producers = new ArrayList<Person>(producers);
	}
	public List<Person> getScriptwriters() {
		return new ArrayList<Person>(scriptwriters);
	}
	public void setScriptwriters(List<Person> scriptwriters) {
		this.scriptwriters = new ArrayList<Person>(scriptwriters);
	}
	public List<Person> getProtagonists() {
		return new ArrayList<Person>(protagonists);
	}
	public void setProtagonists(List<Person> protagonists) {
		this.protagonists = new ArrayList<Person>(protagonists);
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer(super.toString());
		res.append("\nDURATION: " + getDuration() + " min.");
		res.append("\nDIRECTOR: " + getDirector());
		res.append("\nPRODUCTION: ");
		List<Person> producers = getProducers();
		for (Iterator<Person> i = producers.iterator(); i.hasNext();) {
			Person producer = i.next();
			if (i.hasNext()) res.append(producer + ", "); else res.append(producer);
		}
		res.append("\nSCRIPT: ");
		List<Person> scriptwirters = getScriptwriters();
		for (Iterator<Person> i = scriptwirters.iterator(); i.hasNext();) {
			Person scriptwirter = i.next();
			if (i.hasNext()) res.append(scriptwirter + ", "); else res.append(scriptwirter);
		}
		res.append("\nSTARRING: ");
		List<Person> protagonists = getProtagonists();
		for (Iterator<Person> i = protagonists.iterator(); i.hasNext();) {
			Person protagonist = i.next();
			if (i.hasNext()) res.append(protagonist + ", "); else res.append(protagonist);
		}
		return res.toString();
	}
	
}
