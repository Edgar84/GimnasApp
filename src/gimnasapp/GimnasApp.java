
package gimnasapp;

import java.sql.SQLException;

public class GimnasApp {

    public static void main(String[] args) throws SQLException {
        
        Gimnas gim = new Gimnas("Super Gim","C58456858B","9758698521");
        
        gim.gestionarGimnas();
        
    }
    
}
