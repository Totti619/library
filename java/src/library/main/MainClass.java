package library.main;
import java.util.ArrayList;
import java.util.List;

import library.catalogue.Book;
import library.catalogue.Magazine;
import library.catalogue.Movie;
import library.catalogue.Videogame;
import library.catalogue.Element;
import library.data.Company;
import library.data.Country;
import library.data.Person;
import library.data.Subject;

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

		List<Subject> subjects4 = new ArrayList<Subject>();
		subjects4.add(new Subject(1, "Platformer"));
		List<Company> developers = new ArrayList<Company>();
		List<Company> publishers = new ArrayList<Company>();
		List<Person> directors = new ArrayList<Person>();
		List<Person> producers = new ArrayList<Person>();
		List<Person> designers = new ArrayList<Person>();
		List<Person> programmers = new ArrayList<Person>();
		developers.add(new Company(1, "Nintendo EAD", "Videogames", 1));
		developers.add(new Company(1, "Nintendo", "Videogames", 1));
		publishers.add(new Company(1, "Nintendo", "Videogames", 1));
		publishers.add(new Company(1, "SOE", "Videogames", 1));
		directors.add(new Person(1, "Shigeru", "Miyamoto"));
		producers.add(new Person(1, "Shigeru", "Miyamoto"));
		designers.add(new Person(1, "Shigeru", "Miyamoto"));
		designers.add(new Person(1, "Takashi", "Tezuka"));
		programmers.add(new Person(1, "Toshihiko", "Nakago"));
		programmers.add(new Person(1, "Kazuaki", "Morita"));
		
		elements.add(new Videogame(1, "Super Mario Bros", 1985, subjects4, 34, new Country(1, "Japan"), developers, publishers, directors, producers, designers, programmers));
		
		Library lib = new Library("Antonio's Library", elements);
		
		Io.saveLibrary(lib);
		Element e = new Videogame(1, "Super Mario Bros", 1985, subjects4, 34, new Country(1, "Japan"), developers, publishers, directors, producers, designers, programmers);
		Io.saveElementToLibrary(e, "Antonio's Library");
		
		System.out.println(Io.readLibrary("Antonio's Library"));
	}

}
