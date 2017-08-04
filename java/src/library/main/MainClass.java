package library.main;
import library.users.Administrator;
import library.users.User;

public class MainClass {

	public static void main(String[] args) {
		User user = new Administrator("Antonio" , "1234");
		System.out.println(user);
	}

}
