
package gimnasapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Activitat {
    private String nom;
    private String descripcio;
    private int duradaSessio;
    private int reserves;
    private double tantPercent;

    //Constructor
    public Activitat() {
    }
    
    
    public ArrayList<Activitat> veureActivitats() throws SQLException {
        
        ArrayList<Activitat> activitats = new ArrayList<>();
        
        Connection con = ConnexioBD.getConnexioBD();
        PreparedStatement ps = null;
        
        String consulta = "SELECT a.*, count(b.id) AS Reserves, (count(b.id)/(count(b.id) + d.aforament_max) * 100) AS Percent FROM activitat a, reserva_lliure b, es_fa c, sala d WHERE a.id = b.id_act AND c.id = a.id AND c.num = d.num group by a.id;";
        
        ps = con.prepareStatement(consulta);
        ps.executeQuery();
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Activitat actv = new Activitat();
            actv.afegirDadesActivitats(rs);
            activitats.add(actv);
        }
        return activitats;   
    }
    
    private Activitat afegirDadesActivitats(ResultSet rs) throws SQLException {
        
        this.setNom (rs.getString("nom"));
        this.setDescripcio (rs.getString("descripcio"));
        this.setDuradaSessio (rs.getInt("durada_sessio"));
        this.setNumReserves (rs.getInt("Reserves"));
        this.setTantPercent(rs.getInt("Percent"));  

        return this;
    }
    
    // Getters

    public String getNom() {
        return nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public int getDuradaSessio() {
        return duradaSessio;
    }

    public int getReserves() {
        return reserves;
    }

    public double getTantPercent() {
        return tantPercent;
    }
    
    
    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setDuradaSessio(int duradaSessio) {
        this.duradaSessio = duradaSessio;
    }

    public void setNumReserves(int reserves) {
        this.reserves = reserves;
    }

    public void setTantPercent(double tantPercent) {
        this.tantPercent = tantPercent;
    }
    
    
    // Override

    @Override
    public String toString() {
        return "Nom: " + this.nom + "\nDescripció: " + this.descripcio + "\nDurada de la sessió: " + this.duradaSessio + "\nReserves: " + this.reserves + "\nPercentatge: " + this.tantPercent; 
    }
    
    
}
