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
import library.data.Industry;
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
	
	public void saveLibraryIntoDatabase(Library library) throws ClassNotFoundException, SQLException {
		if (getConnection() == null || getConnection().isClosed()) connect();
		Statement st = statement();
		ResultSet rs = null;
		for (Iterator<Element> i = library.getElements().iterator(); i.hasNext();) {
			Element element = i.next();
			Country country = element.getCountry();
			String queryCountry = "insert into country(name) values('" + country.getName() + "')";
			try {
				st.executeUpdate(queryCountry);
			} catch (SQLException e) {
				System.err.println("The program won't insert the country \"" + country.getName() + "\" because it does already exist in this DB.");
			}
			String queryIdCountry = "select id from country where name = '" + country.getName() + "'";
			rs = st.executeQuery(queryIdCountry);
			rs.first();
			int idCountry = rs.getInt(1);
			String queryElement = "insert into element(title, year, stock, country) values('" + element.getTitle() + "', " + element.getYear() + ", " + element.getStock() + ", " + idCountry + ")";
			try {
				st.executeUpdate(queryElement);
			} catch (SQLException e) {
				System.err.println("The program won't insert the element \"" + element.getTitle() + "\" because it does already exist in this DB.");
			}
			String queryIdElement = "select id from element where title = '" + element.getTitle() + "'";
			rs = st.executeQuery(queryIdElement);
			rs.first();
			int idElement = rs.getInt(1);
			if (element instanceof Book) {
				Book book = (Book)element;
				Company publisher = book.getPublishingCompany();
				String queryPublisher = "insert into company(name, number_employees) values('" + publisher.getName() + "', " + publisher.getNumberEmployees() + ")";
				try {
					st.executeUpdate(queryPublisher); // save publisher company into db
				} catch (SQLException e) {
					System.err.println("The program won't insert the publisher \"" + publisher.getName() + "\" because it does already exist in this DB.");
				}
				String queryIdPublisher = "select id from company where name = '" + publisher.getName() + "'";
				rs = st.executeQuery(queryIdPublisher);
				rs.first();
				int idPublisher = rs.getInt(1);
				List<Industry> industries = publisher.getIndustries();
				for (Iterator<Industry> j = industries.iterator(); j.hasNext();) {
					Industry industry = j.next();
					String queryIndustry = "insert into industry(name) values('" + industry.getName() + ")";
					try	{
						st.executeUpdate(queryIndustry);
					} catch (SQLException e) {
						System.err.println("The program won't insert the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
					}
					String queryIdIndustry = "select id from industry where name = '" + industry.getName() + "'";
					rs = st.executeQuery(queryIdIndustry);
					rs.first();
					try {
						int idIndustry = rs.getInt(1);
						String queryCompanyIndustry = "insert into company_industry values (" + idPublisher + ", " + idIndustry + ")";
						try {
							st.executeUpdate(queryCompanyIndustry);
						} catch (SQLException e) {
							System.err.println("The program won't insert the relation between the company \"" + publisher.getName() + "\" and the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
						}
					} catch (SQLException e) {
						System.err.println("There is no data within the instance of the ResultSet.");
					}
				}
				
				
				
				String queryBook = "insert into book values(" + idElement + ", " + book.getIsbn() + ", " + book.getEditionNumber() + ", " + idPublisher + ")";
				try {
					st.executeUpdate(queryBook); // save book company into db
				} catch (SQLException e) {
					System.err.println("The program won't insert the book \"" + book.getTitle() + "\" because it does already exist in this DB.");
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
					} catch (SQLException e) {
						System.err.println("The program won't insert the author \"" + author.getFullName() + "\" because it does already exist in this DB.");
					}
					String queryIdAuthor = "select id from person where name = '" + author.getName() + "' and surnames = '" + author.getSurnames() + "'";
					rs = st.executeQuery(queryIdAuthor);
					rs.first();
					int idAuthor = rs.getInt(1);
					String queryBookAuthor = "insert into book_author values(" + idBook + ", " + idAuthor + ")";
					try {
						st.executeUpdate(queryBookAuthor);
					} catch (SQLException e) {
						System.err.println("The program won't insert the relation between the book \"" + book.getTitle() + "\" and the author \"" + author.getFullName() + "\" because it does already exist in this DB.");
					}
				}
			}
			if (element instanceof Magazine) {
				Magazine magazine = (Magazine)element;
				Company publisher = magazine.getPublishingCompany();
				String queryPublisher = "insert into company(name, number_employees) values('" + publisher.getName() + "', " + publisher.getNumberEmployees() + ")";
				try {
					st.executeUpdate(queryPublisher); // save publisher company into db
				} catch (SQLException e) {
					System.err.println("The program won't insert the publisher \"" + publisher.getName() + "\" because it does already exist in this DB.");
				}
				String queryIdPublisher = "select id from company where name = '" + publisher.getName() + "'";
				
				try {
					st.executeUpdate(queryPublisher); // save publisher company into db
				} catch (SQLException e) {
					System.err.println("The program won't insert the publisher \"" + publisher.getName() + "\" because it does already exist in this DB.");
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
					} catch (SQLException e) {
						System.err.println("The program won't insert the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
					}
					String queryIdIndustry = "select id from industry where name = '" + industry.getName() + "'";
					rs = st.executeQuery(queryIdIndustry);
					rs.first();
					try {
						int idIndustry = rs.getInt(1);
						String queryCompanyIndustry = "insert into company_industry values (" + idPublisher + ", " + idIndustry + ")";
						try {
							st.executeUpdate(queryCompanyIndustry);
						} catch (SQLException e) {
							System.err.println("The program won't insert the relation between the company \"" + publisher.getName() + "\" and the industry \"" + industry.getName() + "\" because it does already exist in this DB.");
						}
					} catch (SQLException e) {
						System.err.println("There is no data within the instance of the ResultSet.");
					}
				}
				
				String queryMagazine = "insert into magazine values(" + idElement + ", " + magazine.getIsbn() + ", " + idPublisher + ")";
				try {
					st.executeUpdate(queryMagazine); // save book company into db
				} catch (SQLException e) {
					System.err.println("The program won't insert the magazine \"" + magazine.getTitle() + "\" because it does already exist in this DB.");
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
					} catch (SQLException e) {
						System.err.println("The program won't insert the founder \"" + founder.getFullName() + "\" because it does already exist in this DB.");
						e.printStackTrace();
					}
					String queryIdFounder = "select id from person where name = '" + founder.getName() + "' and surnames = '" + founder.getSurnames() + "'";
					rs = st.executeQuery(queryIdFounder);
					rs.first();
					int idFounder = rs.getInt(1);
					String queryMagazineFounder = "insert into magazine_founder values(" + idMagazine + ", " + idFounder + ")";
					try {
						st.executeUpdate(queryMagazineFounder);
					} catch (SQLException e) {
						System.err.println("The program won't insert the relation between the magazine \"" + magazine.getTitle() + "\" and the founder \"" + founder.getFullName() + "\" because it does already exist in this DB.");
					}
				}
			}
			if (element instanceof Movie) {
				Movie movie = (Movie)element;
				Person director = movie.getDirector();
				int duration = movie.getDuration();
				
			}
			if (element instanceof Videogame) {
				Videogame videogame = (Videogame)element;
			}
		}
		st.close();
	}
	
	
	
	
}
