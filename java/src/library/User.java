package library;

public class User {
	private String id, name, surname, idCard, telephone, email, address, login, password;
	
	public User() {}
	public User(String id, String name, String surname, String idCard, String telephone, String email, String address,String login, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.idCard = idCard;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.login = login;
		this.password = password;
	}

	private String getId() {return id;}
	private void setId(String id) {this.id = id;}
	private String getName() {return name;}
	private void setName(String name) {this.name = name;}
	private String getSurname() {return surname;}
	private void setSurname(String surname) {this.surname = surname;}
	private String getIdCard() {return idCard;}
	private void setIdCard(String idCard) {this.idCard = idCard;}
	private String getTelephone() {return telephone;}
	private void setTelephone(String telephone) {this.telephone = telephone;}
	private String getEmail() {return email;}
	private void setEmail(String email) {this.email = email;}
	private String getAddress() {return address;}
	private void setAddress(String address) {this.address = address;}
	private String getLogin() {return login;}
	private void setLogin(String login) {this.login = login;}
	private String getPassword() {return password;}
	private void setPassword(String password) {this.password = password;}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
