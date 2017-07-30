package library.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Iterator;
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



public class Db {
	private static Db instance = null;
	private final String DRIVER, URL, USER, PASS;
	private Connection connection = null;
	
	private Db() {
		this.DRIVER = ""; this.URL = ""; this.USER = ""; this.PASS = "";
	}
	private Db(String driver, String url, String user, String pass) {
		this.DRIVER = driver; this.URL = url; this.USER = user; this.PASS = pass;
	}
	public static Db getInstance(String driver, String url, String user, String pass) {
		if (instance == null) instance = new Db(driver, url, user, pass);
		return instance;
	}
	
	public String getDriver() {
		return DRIVER;
	}
	public String getUrl() {
		return URL;
	}
	public String getUser() {
		return USER;
	}
	public String getPass() {
		return PASS;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public boolean connect() throws SQLException {
		setConnection(DriverManager.getConnection(getUrl(), getUser(), getPass()));
		return !getConnection().isClosed();
	}
	public boolean disconnect() throws SQLException {
		getConnection().close();
		return getConnection().isClosed();
	}
	public Statement statement() throws ClassNotFoundException, SQLException {
		Class.forName(getDriver());
		return getConnection().createStatement();
	}
	
	public void saveLibraryIntoDatabase(Library library) throws ClassNotFoundException, SQLException {
		for (Iterator<Element> i = library.getElements().iterator(); i.hasNext();) {
			saveElementFromLibrary(i.next());
		}
	}
	private void saveElementFromLibrary(Element element) throws ClassNotFoundException, SQLException {
		if (getConnection() == null || getConnection().isClosed()) connect();
		ResultSet rs = null;
		Statement st = statement();
		int idElement = saveElement(st, element);
		List<Subject> subjects = element.getSubjects();
		for (Iterator<Subject> i = subjects.iterator(); i.hasNext();) {
			saveSubject(st, idElement, element, i.next());
		}
		if (element instanceof Book) {
			Book book = (Book)element;
			Company publisher = book.getPublishingCompany();
			String queryPublisher = "insert into company(name, number_employees) values('" + publisher.getName() + "', " + publisher.getNumberEmployees() + ")";
			try {
				st.executeUpdate(queryPublisher); // save publisher company into db
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the publisher \"" + publisher.getName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String queryIdPublisher = "select id from company where name = '" + publisher.getName() + "'";
			rs = st.executeQuery(queryIdPublisher);
			rs.first();
			int idPublisher = rs.getInt(1);
			List<Industry> industries = publisher.getIndustries();
			for (Iterator<Industry> j = industries.iterator(); j.hasNext();) {
				Industry industry = j.next();
				String queryIndustry = "insert into industry(name) values('" + industry.getName() + "')";
				try	{
					st.executeUpdate(queryIndustry);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdIndustry = "select id from industry where name = '" + industry.getName() + "'";
				rs = st.executeQuery(queryIdIndustry);
				rs.first();
				try {
					int idIndustry = rs.getInt(1);
					String queryCompanyIndustry = "insert into company_industry values (" + idPublisher + ", " + idIndustry + ")";
					try {
						st.executeUpdate(queryCompanyIndustry);
					} catch (SQLIntegrityConstraintViolationException e) {
						System.err.println("The program won't insert the relation between the company \"" + publisher.getName() + "\" and the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					System.err.println("There is no data within the instance of the ResultSet.");
				}
			}
			
			
			
			String queryBook = "insert into book values(" + idElement + ", " + book.getIsbn() + ", " + book.getEditionNumber() + ", " + idPublisher + ")";
			try {
				st.executeUpdate(queryBook); // save book company into db
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the book \"" + book.getTitle() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			List<Person> authors = book.getAuthors();
			String queryIdBook = "select id from element where title = '" + book.getTitle() + "'";
			rs = st.executeQuery(queryIdBook);
			rs.first();
			int idBook = rs.getInt(1);
			for (Iterator<Person> j = authors.iterator(); j.hasNext();) {
				Person author = j.next();
				String queryAuthor = "insert into person(name, surnames) values('" + author.getName() + "', '" + author.getSurnames() + "')";
				try {
					st.executeUpdate(queryAuthor); // save publisher company into db
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the author \"" + author.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdAuthor = "select id from person where name = '" + author.getName() + "' and surnames = '" + author.getSurnames() + "'";
				rs = st.executeQuery(queryIdAuthor);
				rs.first();
				int idAuthor = rs.getInt(1);
				String queryBookAuthor = "insert into book_author values(" + idBook + ", " + idAuthor + ")";
				try {
					st.executeUpdate(queryBookAuthor);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the relation between the book \"" + book.getTitle() + "\" and the author \"" + author.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (element instanceof Magazine) {
			Magazine magazine = (Magazine)element;
			Company publisher = magazine.getPublishingCompany();
			String queryPublisher = "insert into company(name, number_employees) values('" + publisher.getName() + "', " + publisher.getNumberEmployees() + ")";
			try {
				st.executeUpdate(queryPublisher); // save publisher company into db
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the publisher \"" + publisher.getName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String queryIdPublisher = "select id from company where name = '" + publisher.getName() + "'";
			
			try {
				st.executeUpdate(queryPublisher); // save publisher company into db
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the publisher \"" + publisher.getName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs = st.executeQuery(queryIdPublisher);
			rs.first();
			int idPublisher = rs.getInt(1);
			
			List<Industry> industries = publisher.getIndustries();
			for (Iterator<Industry> j = industries.iterator(); j.hasNext();) {
				Industry industry = j.next();
				String queryIndustry = "insert into industry(name) values('" + industry.getName() + "')";
				try	{
					st.executeUpdate(queryIndustry);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdIndustry = "select id from industry where name = '" + industry.getName() + "'";
				rs = st.executeQuery(queryIdIndustry);
				rs.first();
				try {
					int idIndustry = rs.getInt(1);
					String queryCompanyIndustry = "insert into company_industry values (" + idPublisher + ", " + idIndustry + ")";
					try {
						st.executeUpdate(queryCompanyIndustry);
					} catch (SQLIntegrityConstraintViolationException e) {
						System.err.println("The program won't insert the relation between the company \"" + publisher.getName() + "\" and the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					System.err.println("There is no data within the instance of the ResultSet.");
				}
			}
			
			String queryMagazine = "insert into magazine values(" + idElement + ", " + magazine.getIsbn() + ", " + idPublisher + ")";
			try {
				st.executeUpdate(queryMagazine); // save book company into db
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the magazine \"" + magazine.getTitle() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			List<Person> founders = magazine.getFounders();
			String queryIdMagazine = "select id from element where title = '" + magazine.getTitle() + "'";
			rs = st.executeQuery(queryIdMagazine);
			rs.first();
			int idMagazine = rs.getInt(1);
			for (Iterator<Person> j = founders.iterator(); j.hasNext();) {
				Person founder = j.next();
				String queryFounder = "insert into person(name, surnames) values('" + founder.getName() + "', '" + founder.getSurnames() + "')";
				try {
					st.executeUpdate(queryFounder);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the founder \"" + founder.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdFounder = "select id from person where name = '" + founder.getName() + "' and surnames = '" + founder.getSurnames() + "'";
				rs = st.executeQuery(queryIdFounder);
				rs.first();
				int idFounder = rs.getInt(1);
				String queryMagazineFounder = "insert into magazine_founder values(" + idMagazine + ", " + idFounder + ")";
				try {
					st.executeUpdate(queryMagazineFounder);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the relation between the magazine \"" + magazine.getTitle() + "\" and the founder \"" + founder.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (element instanceof Movie) {
			Movie movie = (Movie)element;
			Person director = movie.getDirector();
			int duration = movie.getDuration();
			String queryDirector = "insert into person(name, surnames) values('" + director.getName() + "', '" + director.getSurnames() + "')";
			try	{
				st.executeUpdate(queryDirector);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the director \"" + director.getFullName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String queryIdDirector = "select id from person where name = '" + director.getName() + "' and surnames = '" + director.getSurnames() + "'";
			rs = st.executeQuery(queryIdDirector);
			rs.first();
			int idDirector = rs.getInt(1);
			String queryMovie = "insert into movie values(" + idElement + ", " + duration + ", " + idDirector + ")";
			try	{
				st.executeUpdate(queryMovie);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the movie \"" + movie.getTitle() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String queryIdMovie = "select id from element where title = '" + movie.getTitle() + "'";
			rs = st.executeQuery(queryIdMovie);
			rs.first();
			int idMovie = rs.getInt(1);
			List<Person> producers = movie.getProducers();
			List<Person> scriptwriters = movie.getScriptwriters();
			List<Person> protagonists = movie.getProtagonists();
			for (Iterator<Person> i = producers.iterator(); i.hasNext();) {
				Person producer = i.next();
				String queryProducer = "insert into person(name, surnames) values('" + producer.getName() + "', '" + producer.getSurnames() + "')";
				try {
					st.executeUpdate(queryProducer);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the producer \"" + producer.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdProducer = "select id from person where name = '" + producer.getName() + "' and surnames = '" + producer.getSurnames() + "'";
				rs = st.executeQuery(queryIdProducer);
				rs.first();
				int idProducer = rs.getInt(1);
				String queryProducerMovie = "insert into movie_produced_by values(" + idMovie + ", " + idProducer + ")";
				try {
					st.executeUpdate(queryProducerMovie);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the relation between the movie \"" + movie.getTitle() + "\" and the producer \"" + producer.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			for (Iterator<Person> i = scriptwriters.iterator(); i.hasNext();) {
				Person scriptwriter = i.next();
				String queryScriptwriter = "insert into person(name, surnames) values('" + scriptwriter.getName() + "', '" + scriptwriter.getSurnames() + "')";
				try {
					st.executeUpdate(queryScriptwriter);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the scriptwriter \"" + scriptwriter.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdScriptwriter = "select id from person where name = '" + scriptwriter.getName() + "' and surnames = '" + scriptwriter.getSurnames() + "'";
				rs = st.executeQuery(queryIdScriptwriter);
				rs.first();
				int idScriptwriter = rs.getInt(1);
				String queryScriptwriterMovie = "insert into movie_scripted_by values(" + idMovie + ", " + idScriptwriter + ")";
				try {
					st.executeUpdate(queryScriptwriterMovie);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the relation between the movie \"" + movie.getTitle() + "\" and the scriptwriter \"" + scriptwriter.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			for (Iterator<Person> i = protagonists.iterator(); i.hasNext();) {
				Person protagonist = i.next();
				String queryProtagonist = "insert into person(name, surnames) values('" + protagonist.getName() + "', '" + protagonist.getSurnames() + "')";
				try {
					st.executeUpdate(queryProtagonist);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the protagonist \"" + protagonist.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String queryIdProtagonist = "select id from person where name = '" + protagonist.getName() + "' and surnames = '" + protagonist.getSurnames() + "'";
				rs = st.executeQuery(queryIdProtagonist);
				rs.first();
				int idProtagonist = rs.getInt(1);
				String queryProtagonistMovie = "insert into movie_starred_by values(" + idMovie + ", " + idProtagonist + ")";
				try {
					st.executeUpdate(queryProtagonistMovie);
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("The program won't insert the relation between the movie \"" + movie.getTitle() + "\" and the protagonist \"" + protagonist.getFullName() + "\" because it does already exist in this DB.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (element instanceof Videogame) {
			saveVideogame(st, (Videogame)element, idElement);
		}
		rs.close(); st.close();
	}
	private void saveVideogame(Statement st, Videogame videogame, int idElement) throws SQLException {
		String queryVideogame = "insert into videogame(id) values(" + idElement + ")";
	
		List<Company> developingCompanies = videogame.getDevelopingCompanies();
		List<Company> publishingCompanies = videogame.getPublishingCompanies();
		List<Person> directors = videogame.getDirectors();
		List<Person> producers = videogame.getProducers();
		List<Person> designers = videogame.getDesigners();
		List<Person> programmers = videogame.getProgrammers();
		for (Iterator<Company> i = developingCompanies.iterator(); i.hasNext();) {
			Company company = i.next();
			int idDevelopingCompany = saveCompany(company);
			String queryVideogameDevelopingCompany = "insert into videogame_developed_by values(" + idElement + ", " + idDevelopingCompany + ")";
			try {
				if (st != null) st.executeUpdate(queryVideogameDevelopingCompany);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the relation between the videogame \"" + videogame.getTitle() + "\" and the developing company \"" + company.getName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Iterator<Company> i = publishingCompanies.iterator(); i.hasNext();) {
			Company company = i.next();
			int idPublishingCompany = saveCompany(company);
			String queryVideogamePublishingCompany = "insert into videogame_published_by values(" + idElement + ", " + idPublishingCompany + ")";
			try {
				if (st != null) st.executeUpdate(queryVideogamePublishingCompany);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the relation between the videogame \"" + videogame.getTitle() + "\" and the publishing company \"" + company.getName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Iterator<Person> i = directors.iterator(); i.hasNext();) {
			Person person = i.next();
			int idDirector = savePerson(person);
			String queryVideogameDirector = "insert into videogame_directed_by values(" + idElement + ", " + idDirector + ")";
			try {
				if (st != null) st.executeUpdate(queryVideogameDirector);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("The program won't insert the relation between the videogame \"" + videogame.getTitle() + "\" and the director \"" + person.getFullName() + "\" because it does already exist in this DB.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// TODO saveProducers(producers, idElement);
		// TODO saveDesigners(designers, idElement);
		// TODO saveProgrammers(programmers, idElement);
	}
	private int savePerson(Person person) {
		ResultSet rs = null; Statement st = null;
		int idPerson = 0;
		try {
			st = statement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryPerson = "insert into person(name, surnames) values('" + person.getName() + "', '" + person.getSurnames() + "')";
		try {
			if (st != null) st.executeUpdate(queryPerson);
			String queryIdPerson = "select id from person where name = '" + person.getName() + "' and surnames = '" + person.getSurnames() + "'";
			rs = st.executeQuery(queryIdPerson);
			rs.first();
			idPerson = rs.getInt(1);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the person \"" + person.getFullName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idPerson;
	}
	private int saveCompany(Company company) {
		ResultSet rs = null; Statement st = null;
		int idCompany = 0;
		try {
			st = statement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryCompany = "insert into company(name, number_employees) values('" + company.getName() + "', " + company.getNumberEmployees() + ")"; 
		try {
			if (st != null) st.executeUpdate(queryCompany);
			String queryIdCompany = "select id from company where name = '" + company.getName() + "'";
			rs = st.executeQuery(queryIdCompany);
			rs.first();
			idCompany = rs.getInt(1);
			List<Industry> industries = company.getIndustries();
			for (Iterator<Industry> i = industries.iterator(); i.hasNext();) {
				Industry industry = i.next();
				int idIndustry = saveIndustry(industry);
				saveCompanyIndustry(company, industry, idCompany, idIndustry);
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the company \"" + company.getName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idCompany;
	}
	private void saveCompanyIndustry(Company company, Industry industry, int idCompany, int idIndustry) {
		Statement st = null;
		try {
			st = statement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryCompanyIndustry = "insert into company_industry values(" + idCompany + ", " + idIndustry + ")";
		try {
			st.executeUpdate(queryCompanyIndustry);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the relation between the company \"" + company.getName() + "\" and the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private int saveIndustry(Industry industry) {
		ResultSet rs = null; Statement st = null;
		try {
			st = statement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryIndustry = "insert into industry(name) values('" + industry.getName() + "')";
		try {
			if (st != null) st.executeUpdate(queryIndustry);
			String queryIdIndustry = "select id from company where name = '" + industry.getName() + "'";
			rs = st.executeQuery(queryIdIndustry);
			rs.first();
			return rs.getInt(1);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private void saveSubject(Statement st, int idElement, Element element, Subject subject) throws SQLException {
		ResultSet rs;
		String querySubject = "insert into subject(name) values('" + subject.getName() + "')";
		try {
			st.executeUpdate(querySubject);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the subject \"" + subject.getName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryIdSubject = "select id from subject where name = '" + subject.getName() + "'";
		rs = st.executeQuery(queryIdSubject);
		rs.first();
		int idSubject = rs.getInt(1);
		String querySubjectElement = "insert into element_subject values(" + idElement + ", " + idSubject + ")";
		try {
			st.executeUpdate(querySubjectElement);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the relation between the element \"" + element.getTitle() + "\" and the subject \"" + subject.getName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private int saveElement(Statement st, Element element) throws SQLException {
		ResultSet rs;
		Country country = element.getCountry();
		int idCountry = saveCountry(st, country);
		String queryElement = "insert into element(title, year, stock, country) values('" + element.getTitle() + "', " + element.getYear() + ", " + element.getStock() + ", " + idCountry + ")";
		try {
			st.executeUpdate(queryElement);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the element \"" + element.getTitle() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryIdElement = "select id from element where title = '" + element.getTitle() + "'";
		rs = st.executeQuery(queryIdElement);
		rs.first();
		return rs.getInt(1);
	}
	private int saveCountry(Statement st, Country country) throws SQLException {
		ResultSet rs;
		String queryCountry = "insert into country(name) values('" + country.getName() + "')";
		try {
			st.executeUpdate(queryCountry);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("The program won't insert the country \"" + country.getName() + "\" because it does already exist in this DB.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String queryIdCountry = "select id from country where name = '" + country.getName() + "'";
		rs = st.executeQuery(queryIdCountry);
		rs.first();
		return rs.getInt(1);
	}
	
	
	
	
}
