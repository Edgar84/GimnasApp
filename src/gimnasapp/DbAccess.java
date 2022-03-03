
package gimnasapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DbAccess {
    
    private String url;
    private String schema;
    private String user;
    private String pass;

    static Connection conn = null;

    public void getConnection(){
        
        url = "jdbc:mysql://localhost:3307/";
        schema = "db_gimnas";
        user = "root";
        pass = "root";
        
        try {
            conn = DriverManager.getConnection(url + schema, user, pass);
        } catch (SQLException ex) {
            System.out.println("Ha hagut algún problema amb la conexió a la BD");
        } catch (Exception e) {
            System.out.println("Ha hagut algún problema amb la aplicació");
        }
    }
    
    public void closeConnection() throws SQLException{
        conn.close();
    }
    
    
/*
    private DbAccess(){
        String url = "jdbc:mysql://localhost:3307/db_gimnas";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "root";

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    //Obrir conexió
    
    public static Connection getConnection(){
        if (conn == null){
            new DbAccess();
        }
        return conn;
    }
    
    
    //Tancar conexió
    
    public void closeConnection() throws SQLException{
        if (conn != null){
            conn.close();
        }
    }*/
}
/*
// Per cridar a la connexió: Connection conn = DbAccess.getConnection();
try {
     Connection c = DbAccess.getConnection();
     PreparedStatement st;
     st = c.prepareStatement("INSERT INTO NombreTable VALUES (?,?,?)");
     st.setInt(1, 1234);                //el 1 indica que se sustituira el primer '?' con el valor en int de 1234
     st.setDouble(2, 123.45);           //el 2 indica que se sustituira el segundo '?' por el valor en double de 123.45 
     st.setString(3, "hola");           //el 3 indica que se sustiruira el tercer '?' por la cadena "hola"
    //los tipos de variables deben coincidir con los tipos definidos en las columnas de la tabla en la que se insertaran
    if(st.executeUpdate()==1){
       System.out.println("fila insertada correctamente");
     }
 } 
 catch (SQLException ex) {
   //captura la excepcion
 }
*/