package library.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import library.data.Person;



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
	
	public boolean saveLibraryIntoDatabase(Library library) throws ClassNotFoundException, SQLException {
		if (getConnection() == null || getConnection().isClosed()) connect();
		Statement st = statement();
		ResultSet rs = null;
		for (Iterator<Element> i = library.getElements().iterator(); i.hasNext();) {
			Element element = i.next();
			Country country = element.getCountry();
			String queryCountry = "insert into country(name) values('" + country.getName() + "')";
			st.executeUpdate(queryCountry);
			String queryIdCountry = "select id from country where name = '" + country.getName() + "'";
			rs = st.executeQuery(queryIdCountry);
			rs.first();
			int idCountry = rs.getInt(1);
			String queryElement = "insert into element(title, year, stock, country) values('" + element.getTitle() + "', " + element.getYear() + ", " + element.getStock() + ", " + idCountry + ")";
			st.executeUpdate(queryElement);
			String queryIdElement = "select id from element where title = '" + element.getTitle() + "'";
			rs = st.executeQuery(queryIdElement);
			rs.first();
			int idElement = rs.getInt(1);
			if (element instanceof Book) {
				Book book = (Book)element;
				Company publisher = book.getPublishingCompany();
				String queryPublisher = "insert into company(name, number_employees) values('" + publisher.getName() + "', " + publisher.getNumberEmployees() + ")";
				String queryIdPublisher = "select id from company where name = '" + publisher.getName() + "'";
				
				st.executeUpdate(queryPublisher); // save publisher company into db
				rs = st.executeQuery(queryIdPublisher);
				rs.first();
				int idPublisher = rs.getInt(1);
				
				String queryBook = "insert into book values(" + idElement + ", " + book.getIsbn() + ", " + book.getEditionNumber() + ", " + idPublisher + ")";
				st.executeUpdate(queryBook); // save book company into db
		
				List<Person> authors = book.getAuthors();
				String queryIdBook = "select id from element where title = '" + book.getTitle() + "'";
				rs = st.executeQuery(queryIdBook);
				rs.first();
				int idBook = rs.getInt(1);
				for (Iterator<Person> j = authors.iterator(); j.hasNext();) {
					Person author = j.next();
					String queryAuthor = "insert into person(name, surnames) values('" + author.getName() + "', '" + author.getSurnames() + "')";
					st.executeUpdate(queryAuthor);
					String queryIdAuthor = "select id from person where name = '" + author.getName() + "' and surnames = '" + author.getSurnames() + "'";
					rs = st.executeQuery(queryIdAuthor);
					rs.first();
					int idAuthor = rs.getInt(1);
					String queryBookAuthor = "insert into book_author values(" + idBook + ", " + idAuthor + ")";
					st.executeUpdate(queryBookAuthor);
				}
			}
			if (element instanceof Magazine) {
							
			}
			if (element instanceof Movie) {
				
			}
			if (element instanceof Videogame) {
				
			}
		}
		st.close();
		return false;
	}
	
	
	
	
}
