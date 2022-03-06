
package gimnasapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class ConnexioBD {
    private String servidor;
    private String ddbb;
    private String user;
    private String pass;

    static Connection connexioBD = null;
    
    private  ConnexioBD(){
        servidor = "jdbc:mysql://127.0.0.1:3307/db_gimnas";
        //ddbb = "db_gimnas";
        user = "root";
        pass = "root";
        
        try {
            connexioBD = DriverManager.getConnection(servidor, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnexioBD() {
        if (connexioBD ==null){
            new ConnexioBD();
        }
        return connexioBD;
        
    }
    
    
    
}
