
package gimnasapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    // Atributs
    private String nom;
    private String cognom;
    private Dni dni;
    private int telefon;
    private Email email;
    private String sexe;
    private LocalDate dataNeixement;
    private String usuari;
    private String pass;
    private CompteBancari compteBancari;
    private String condicio;
    private boolean comunicacioComercial;
    
    static ConnexioBD con = new ConnexioBD();

    // Constructors
    public Client(String nom, String cognom, Dni dni, int telefon, Email email, String sexe,LocalDate dataNeixement, String usuari, String pass, CompteBancari compteBancari, String condicio, boolean comunicacioComercial) {
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
        this.telefon = telefon;
        this.email = email;
        this.sexe = sexe;
        this.dataNeixement = dataNeixement;
        this.usuari = usuari;
        this.pass = pass;
        this.compteBancari = compteBancari;
        this.condicio = condicio;
        this.comunicacioComercial = comunicacioComercial;
    }
    public Client() {
        
    }
    
    
    // Functions
    public void altaClient(){
        Scanner teclat = new Scanner(System.in); 
        boolean continua = false;
        
        System.out.println("*******DONAR D'ALTA*******");
        
        System.out.println("Nom:");
        this.nom = teclat.nextLine();
        
        System.out.println("Cognom:");
        this.cognom = teclat.nextLine();
        
        String dni = "";
        Dni dninn = new Dni();
        do{
            System.out.println("DNI:");
            dni = teclat.nextLine();

            while (!dninn.validarDni(dni)) {                    
                System.out.println("El DNI introduït no es correcte");
                dni = teclat.nextLine();
            }
            dninn.setNum(dni);
            this.dni = dninn;
            continua = true;
        }while (!continua);
        
        int tel = 0;
        do{
            try {
                System.out.println("Telèfon:");
                tel = teclat.nextInt();
                
                while (String.valueOf(tel).length() != 9){
                    System.out.println("El telèfon introduït no es correcte");
                    tel = teclat.nextInt();
                }
                continua = true;
            }catch (InputMismatchException ex){
                System.out.println("Només pot ingresar números enters");
                teclat.next();
                continua = false;
            }
        }while (!continua);
        
        teclat.nextLine();
        
        String email = "";
        do{
            System.out.println("Email:");
            email = teclat.nextLine();

            while (!new Email(email).validarEmail()) {                    
                System.out.println("El correu introduït no es correcte");
                email = teclat.nextLine();
            }
            continua = true;
        }while (!continua);
        
        this.sexe = "";
        do {
            try {
                while (this.sexe.equals("")) {   
                    System.out.println("Sexe:");
                    System.out.println("1- Home");
                    System.out.println("2- Dona");
                    int opcioMenu = teclat.nextInt();
                    continua = true;

                    switch(opcioMenu){
                        case 1:
                            System.out.println("Home");
                            this.sexe = "H";
                        case 2:
                            System.out.println("Dona");
                            this.sexe = "D";
                            break;
                        default:
                            System.out.println("Opció incorrecta");
                            sexe = "";
                            break;
                    }
                }
            } catch(InputMismatchException ex){
                System.out.println("Només pots escollir entre Home(1) o Dona(2)");
                teclat.next();
                continua = false;
            }
        }while (!continua);
        
        teclat.nextLine();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean dataCorrecta;
        
        do{
            dataCorrecta = true;
            System.out.println("Data de Neixement:");
            System.out.println("(Format: DD/MM/YYYY)");
            try{
                this.dataNeixement = LocalDate.parse(teclat.next(),formatter);
            }catch (Exception ex){
                dataCorrecta = false;
            }
        }while (!dataCorrecta);       
        
        teclat.nextLine();
        
        System.out.println("Usuari:");
        this.usuari = teclat.nextLine();
        
        System.out.println("Contrasenya:");
        this.pass = teclat.nextLine();        
        
        String compte = "";
        CompteBancari compteBancariVal = new CompteBancari();
        do{
            System.out.println("Compte bancari:");
            compte = teclat.nextLine();

            while (compteBancariVal.validarIBAN(compte)) {                    
                System.out.println("El Compte bancari introduït no es correcte");
                compte = teclat.nextLine();
            }
            compteBancariVal.setNumCompte(compte);
            this.compteBancari = compteBancariVal;
            continua = true;
        }while (!continua);       

        System.out.println("Condició:");
        String condicio = teclat.nextLine();
       
        boolean comunicacioComercial = false;
        String comunicacio = "";
        do {
            try {
                while (comunicacio.equals("")) {   
                    System.out.println("Comunicació comercial:");
                    System.out.println("1- Si");
                    System.out.println("2- No");
                    int opcioMenu = teclat.nextInt();
                    continua = true;

                    switch(opcioMenu){
                        case 1:
                            System.out.println("Si, vull comunicació comercial.");
                            comunicacio = "1";
                            comunicacioComercial = true;
                        case 2:
                            System.out.println("No, no en vull.");
                            comunicacio = "2";
                            comunicacioComercial = false;
                            break;
                        default:
                            System.out.println("Opció incorrecta");
                            sexe = "";
                            break;
                    }
                }
            } catch(InputMismatchException ex){
                System.out.println("Només pots escollir entre Si(1) o No(2)");
                teclat.next();
                continua = false;
            }
        }while (!continua);
        
        insertarClient();
    }
    
    private void insertarClient(){
        
        try {
            
            String consulta = "INSERT INTO client (dni, nom, cognom, telefon, email, sexe, data_neixement, usuari, contrasenya, compte_bancari, condicio, comunicacio_comercial) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = con.connexioBD.prepareStatement(consulta);
            st.setString(1, this.dni.getNum());                
            st.setString(2, this.nom);
            st.setString(3, this.cognom);
            st.setInt(4, this.telefon);
            st.setString(5, this.email.getEmail());
            st.setString(6, this.sexe);
            st.setDate(7, Date.valueOf(this.dataNeixement));
            st.setString(8, this.usuari);
            st.setString(9, this.pass);
            st.setString(10, this.compteBancari.getNumCompte());
            st.setString(11, this.condicio);
            st.setBoolean(12, this.comunicacioComercial);
            
            st.executeUpdate();
            if(st.executeUpdate()==1){
               System.out.println("Client afegit correctament");
             }
        } 
        catch (SQLException ex) {
            System.out.println("No s'ha pogut afegir el client a la Base de dades");
        }
    }
    
    private void llistarClients(){
        
        PreparedStatement ps = null;
        try{

            String consulta = "SELECT * FROM clients ORDER BY nom;";
            ps = con.getConnection.prepareStatement(consulta);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                System.out.println("--------------");
                System.out.print("DNI: " + rs.getInt("dni") + " | ");
                System.out.print("Nom: " + rs.getString("nom") + " | ");
                System.out.print("Cognom: " + rs.getInt("cognom") + " | ");
                System.out.print("Telèfon: " + rs.getString("telefon") + " | ");
                System.out.print("Usuari: " + rs.getDouble("usuari") + " | ");
                System.out.println("Sexe: " + rs.getString("sexe") + " | ");
            }
            System.out.println("--------------");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        
        
    }
    
    @Override
    public String toString(){
        return "Client {Nom:" + this.nom + ", Cognom: " + this.cognom + ", DNI: " + this.dni + ", Tel: " + this.telefon + ", Email: " + this.email + ", sexe: " + this.sexe + ", Usuari: " + this.usuari + ", Pass: ******, Compte Bancari: " + this.compteBancari + ", Condició: " + this.condicio + ", Comunicació comercial: " + this.comunicacioComercial;
    }
    
}
