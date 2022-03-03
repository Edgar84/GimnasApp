
package gimnasapp;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnexioBD {
    private String servidor;
    private String ddbb;
    private String user;
    private String pass;

    static Connection connexioBD = null;
    
    public void connexioBD(){
        servidor = "jdbc:mysql://localhost:3307/";
        ddbb = "db_gimnas";
        user = "root";
        pass = "root";
        
        try {
            connexioBD = DriverManager.getConnection(servidor + ddbb, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
