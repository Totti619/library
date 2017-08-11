/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.users;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.main.Db;

/**
 *
 * @author Master
 */
public class Login {
    private User user;
    public static Db db = Db.getInstance("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3307/library", "root", "");
    
    public Login(String user, String pass) {
        try {
            db.connect();
            setUser(db.selectUser(user, pass));
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
