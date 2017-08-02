package library.main;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.catalogue.Book;
import library.catalogue.Element;
import library.catalogue.Magazine;
import library.catalogue.Movie;
import library.catalogue.Videogame;
import library.data.Company;
import library.data.Country;
import library.data.Industry;
import library.data.Person;
import library.data.Subject;

public class MainClass {

	public static void main(String[] args) {

		List<Element> elements = new ArrayList<Element>();
		List<Subject> subjects = new ArrayList<Subject>();
		List<Industry> industries = new ArrayList<Industry>();
		industries.add(new Industry(1, "Videogames"));
		industries.add(new Industry(1, "Games"));
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
		List<Person> authors = new ArrayList<Person>();
		authors.add(new Person(1, "Miguel", "De Cervantes"));
		authors.add(new Person(1, "Another", "Sample Author"));
		elements.add(new Book(1, "The Ingenious Nobleman Mister Quixote of La Mancha", 1605, subjects2, 12, new Country(1, "Spain"), "9780805511963", authors, new Company(1, "Tu editorial", industries, 2), 34));
		
		List<Subject> subjects3 = new ArrayList<Subject>();
		subjects3.add(new Subject("News magazine"));
		subjects3.add(new Subject("Shit"));
		List<Person> founders = new ArrayList<Person>();
		founders.add(new Person(1, "Manolo", "Ortega"));
		founders.add(new Person(1, "Antonio", "Chavez"));
		founders.add(new Person(1, "Antonio", "Asensio Pizarro"));
		elements.add(new Magazine(1, "Interviu", 1976, subjects3, 196, new Country(1, "Spain"), "9788485286256", founders, new Company(1, "Grupo Zeta", industries, 69)));

		List<Subject> subjects4 = new ArrayList<Subject>();
		subjects4.add(new Subject(1, "Platformer"));
		List<Company> developers = new ArrayList<Company>();
		List<Company> publishers = new ArrayList<Company>();
		List<Person> directors = new ArrayList<Person>();
		List<Person> producers = new ArrayList<Person>();
		List<Person> designers = new ArrayList<Person>();
		List<Person> programmers = new ArrayList<Person>();
		
		developers.add(new Company(1, "Nintendo EAD", industries, 1));
		developers.add(new Company(1, "Nintendo", industries, 1));
		publishers.add(new Company(1, "Nintendo", industries, 1));
		publishers.add(new Company(1, "SOE", industries, 1));
		directors.add(new Person(1, "Shigeru", "Miyamoto"));
		producers.add(new Person(1, "Shigeru", "Miyamoto"));
		designers.add(new Person(1, "Shigeru", "Miyamoto"));
		designers.add(new Person(1, "Takashi", "Tezuka"));
		programmers.add(new Person(1, "Toshihiko", "Nakago"));
		programmers.add(new Person(1, "Kazuaki", "Morita"));
		
		elements.add(new Videogame(1, "Super Mario Bros", 1985, subjects4, 34, new Country(1, "Japan"), developers, publishers, directors, producers, designers, programmers));
		
		List<Subject> subjects5 = new ArrayList<Subject>();
		subjects5.add(new Subject(1, "Action RPG"));
		subjects5.add(new Subject(1, "Open world"));
		List<Company> developers1 = new ArrayList<Company>();
		List<Company> publishers1 = new ArrayList<Company>();
		List<Person> directors1 = new ArrayList<Person>();
		List<Person> producers1 = new ArrayList<Person>();
		List<Person> designers1 = new ArrayList<Person>();
		List<Person> programmers1 = new ArrayList<Person>();
		
		developers1.add(new Company(1, "Bethesda Game Studios", industries, 1));
		publishers1.add(new Company(1, "Bethesda Softworks", industries, 1));
		publishers1.add(new Company(1, "Steam", industries, 1));
		directors1.add(new Person(1, "Todd", "Howard"));
		producers1.add(new Person(1, "Craig", "Laffety"));
		designers1.add(new Person(1, "Bruce", "Nesmith"));
		designers1.add(new Person(1, "Kurt", "Kuhlmann"));
		programmers1.add(new Person(1, "Guy", "Carver"));
		
		elements.add(new Videogame(1, "The Elder Scrolls V: Skyrim", 2011, subjects5, 999, new Country(1, "United States"), developers1, publishers1, directors1, producers1, designers1, programmers1));
		
		Library lib = new Library("Antonio's Library", elements);
		
		/*
		Io.saveLibrary(lib);
		Element e = new Videogame(1, "Super Mario Bros", 1985, subjects4, 34, new Country(1, "Japan"), developers, publishers, directors, producers, designers, programmers);
		Io.saveElementToLibrary(e, "Antonio's Library");
		
		System.out.println(Io.readLibraries());
		*/
		Db db = Db.getInstance("com.mysql.jdbc.Driver", "localhost", (short)3307, "root", "");
		try {
			if (db.connect()) System.out.println("Connected to localhost:3307/library with success!"); else System.err.println("Error with the connection");
			db.saveLibraryIntoDatabase(lib);
			if (db.disconnect()) System.out.println("Disconnected to localhost:3307/library with success!"); else System.err.println("Error with the disconnection");
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		
	}

}
