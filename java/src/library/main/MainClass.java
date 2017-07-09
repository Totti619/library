package library.main;
import java.util.ArrayList;
import java.util.List;

import library.catalogue.*;
import library.data.Company;
import library.data.Country;
import library.data.Person;
import library.data.Subject;
import library.users.*;

public class MainClass {

	public static void main(String[] args) {
		List<Element> elements = new ArrayList<Element>();
		List<Subject> subjects = new ArrayList<Subject>();
		
		List<Person> production = new ArrayList<Person>();
		List<Person> script = new ArrayList<Person>();
		List<Person> starring = new ArrayList<Person>();
		subjects.add(new Subject("Black comedy"));
		subjects.add(new Subject("Neo-noir"));
		subjects.add(new Subject("Crime film"));
		production.add(new Person(1, "Lawrence", "Bender"));
		script.add(new Person(1, "Quentin", "Tarantino"));
		script.add(new Person(1, "Roger", "Avary"));
		starring.add(new Person(1, "John", "Travolta"));
		starring.add(new Person(1, "Uma", "Thurman"));
		elements.add(new Movie(1, "Pulp Fiction", 1994, subjects, 9, new Country(1, "United States"), new Person(1, "Quentin", "Tarantino"), production, script, starring, 158));
		
		List<Subject> subjects2 = new ArrayList<Subject>();
		subjects2.add(new Subject("Novel"));
		elements.add(new Book(1, "The Ingenious Nobleman Mister Quixote of La Mancha", 1605, subjects2, 12, new Country(1, "Spain"), "9780805511963", new Person(1, "Miguel", "De Cervantes"), new Company(1, "Tu editorial", "Books", 2), 34));
		
		List<Subject> subjects3 = new ArrayList<Subject>();
		subjects3.add(new Subject("News magazine"));
		subjects3.add(new Subject("Shit"));
		elements.add(new Magazine(1, "Interviu", 1976, subjects3, 196, new Country(1, "Spain"), "9788485286256", new Person(1, "Antonio", "Asensio Pizarro"), new Company(1, "Grupo Zeta", "Magazines", 69)));
		
		Library lib = new Library("Antonio's Library", elements);
		
		System.out.println(lib);
	}

}
