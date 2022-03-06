
package gimnasapp;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import jdk.nashorn.internal.ir.BreakNode;

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
    private int edat;
    private int reserves;
    
    //static ConnexioBD con = new ConnexioBD();

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
    public void altaClient() throws SQLException, NoSuchAlgorithmException{
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
            if(ConsultaConsultarClients(dni) != null){
                System.out.println("Aquest DNI ja está a la base de dades");
            }else{
                continua = true;
            }
            
        }while (!continua);
        
        dninn.setNum(dni);
        this.dni = dninn;
        
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
        
  
        Email email = new Email();
        String mail = "";
        do{
            System.out.println("Email:");
            mail = teclat.nextLine();

            while (!email.validarEmail(mail)) {                    
                System.out.println("El correu introduït no es correcte");
                mail = teclat.nextLine();
            }
            email.setEmail(mail);
            this.email = email;
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
        this.pass = encriptarPass(teclat.nextLine());        
        
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
                            break;
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
    
    private void insertarClient() throws SQLException{
        
        Connection con = ConnexioBD.getConnexioBD();
        PreparedStatement st = null;
        String consulta = "";
        consulta = "INSERT INTO client (dni, nom, cognom, telefon, email, sexe, data_neixement, usuari, contrasenya, compte_bancari, condicio, comunicacio_comercial) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
           
            st = con.prepareStatement(consulta);
            st.setString(1, this.dni.getNum());                
            st.setString(2, this.nom);
            st.setString(3, this.cognom);
            st.setInt(4, this.telefon);
            st.setString(5, email.getEmail());
            st.setString(6, this.sexe);
            st.setDate(7, Date.valueOf(this.dataNeixement));
            st.setString(8, this.usuari);
            st.setString(9, this.pass);
            st.setString(10, this.compteBancari.getNumCompte());
            st.setString(11, this.condicio);
            st.setBoolean(12, this.comunicacioComercial);
            
            st.executeUpdate();
            
            
        }catch (SQLException ex) {
            System.out.println("No s'ha pogut afegir el client a la Base de dades" + st);
        }finally {
            if (st != null){
                System.out.println("\nClient afegit correcatment:");
            }
        }
        
    }
    
    public void consultarClients(){
        Scanner teclat = new Scanner(System.in);
        System.out.println("Introdueix el DNI a consultar:");
        
        String dni = teclat.nextLine();
        Client clientTrobat = ConsultaConsultarClients(dni);

        if (clientTrobat != null) {
            System.out.println(clientTrobat.toString());
        } else {
            System.out.println("Aquest client no es troba la base de dades");
        } 
    }
    
    private Client ConsultaConsultarClients(String dni){
        
        Connection con = ConnexioBD.getConnexioBD();
        PreparedStatement ps = null;
        String consulta = "SELECT * FROM client WHERE dni = ?;";
        
        try{
            ps = con.prepareStatement(consulta);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                afegirDadesAClient(rs);
                return this;
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<Client> ordenarPerCognom(){
        
        ArrayList<Client> clients = new ArrayList<>();

        Connection con = ConnexioBD.getConnexioBD();
        PreparedStatement ps = null;
        
        try{
            String consulta = "SELECT * FROM client ORDER BY cognom;";
            ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Client clnt = new Client();
                clnt.afegirDadesAClient(rs);
                clients.add(clnt);
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return  clients;
        
    }
            
    public ArrayList<Client> ordenarPerEdat(){
        
        ArrayList<Client> clients = new ArrayList<>();

        Connection con = ConnexioBD.getConnexioBD();
        PreparedStatement ps = null;
        
        try{
            String consulta = "SELECT * FROM client ORDER BY data_neixement;";
            ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Client clnt = new Client();
                clnt.afegirDadesAClient(rs);
                clients.add(clnt);
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return  clients;
        
    }
            
    public ArrayList<Client> ordenarPerReserves(){
        
        ArrayList<Client> clients = new ArrayList<>();

        Connection con = ConnexioBD.getConnexioBD();
        PreparedStatement ps = null;
        
        try{
            String consulta = "SELECT client.*,count(reserva_colectiva.dni) + (SELECT count(reserva_lliure.dni) FROM reserva_lliure WHERE reserva_lliure.dni = client.dni)as Reserves FROM reserva_colectiva, client WHERE reserva_colectiva.dni = client.dni GROUP BY reserva_colectiva.dni  ORDER BY count(reserva_colectiva.dni) + (SELECT count(reserva_lliure.dni) FROM reserva_lliure WHERE reserva_lliure.dni = client.dni) desc;";
            ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Client clnt = new Client();
                clnt.afegirDadesAClient(rs);
                clnt.setReserves(rs.getInt("Reserves"));
                clients.add(clnt);
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return  clients;
        
    }
    
    public void baixaClient() {
        Scanner teclat = new Scanner(System.in);
        Connection con = ConnexioBD.getConnexioBD();
        Boolean continua = false;
        System.out.print("DNI del client per donar de baixa: ");
        
        LocalDate dataAvui = LocalDate.now();
        PreparedStatement ps = null;
        
        Dni dninn = new Dni();
        String dni = "";
        
        do{
            System.out.println("DNI:");
            dni = teclat.nextLine();

            while (!dninn.validarDni(dni)) {                    
                System.out.println("Introdueix un DNI correcte:");
                dni = teclat.nextLine();
            }
            continua = true;
        }while (!continua);
        
        
        String consulta = "UPDATE es_dona SET data_baixa = ? WHERE dni = ?;";
        
        try {
            ps = con.prepareStatement(consulta);
            ps.setDate(1, Date.valueOf(dataAvui));
            ps.setString(2, dni);
            
            ps.executeUpdate();
            
        }catch (SQLException ex) {
            System.out.println("No s'ha pogut donar de baixa el client");
        }finally {
            if (ps != null){
                System.out.println("\nEl client " + dni + " ha sigut donat de baixa.\n");
            }
        }
        
    }
    
    
    private Client afegirDadesAClient(ResultSet rs) throws SQLException {
        
        this.setNom(rs.getString("nom"));
        this.setCognom(rs.getString("cognom"));
        this.setDni(new Dni(rs.getString("dni")));  // Crear objecte per DNI
        this.setTelefon(rs.getInt("telefon"));
        this.setEmail(new Email(rs.getString("email")));    // Crear objecte per Email
        this.setSexe(rs.getString("sexe"));
        this.setDataNeixement(rs.getDate("data_neixement").toLocalDate());
        this.setUsuari(rs.getString("usuari"));
        this.setPass(rs.getString("contrasenya"));
        this.setCompteBancari(new CompteBancari(rs.getString("compte_bancari")));    // Crear objecte per Compte bancari
        this.setCondicio(rs.getString("condicio"));
        this.setComunicacioComercial(rs.getBoolean("comunicacio_comercial"));
        
        calcularEdat();
        
        return this;
    }
    
    private String encriptarPass(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        return bigInt.toString(16);
    
    }
    
    private void calcularEdat() {
        LocalDate avui = LocalDate.now();
        this.edat = Period.between(this.dataNeixement, avui).getYears();
    }
    
    
    
    // Getters

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public Dni getDni() {
        return dni;
    }

    public int getTelefon() {
        return telefon;
    }

    public Email getEmail() {
        return email;
    }

    public String getSexe() {
        return sexe;
    }

    public LocalDate getDataNeixement() {
        return dataNeixement;
    }

    public CompteBancari getCompteBancari() {
        return compteBancari;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getPass() {
        return pass;
    }

    public String getCondicio() {
        return condicio;
    }

    public boolean isComunicacioComercial() {
        return comunicacioComercial;
    }

    public int getEdat() {
        return edat;
    }

    public int getReserves() {
        return reserves;
    }
    
    
    
    
    // Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDataNeixement(LocalDate dataNeixement) {
        this.dataNeixement = dataNeixement;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setCompteBancari(CompteBancari compteBancari) {
        this.compteBancari = compteBancari;
    }

    public void setCondicio(String condicio) {
        this.condicio = condicio;
    }

    public void setComunicacioComercial(boolean comunicacioComercial) {
        this.comunicacioComercial = comunicacioComercial;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }
    
    public void setReserves(int reserves) {
        this.reserves = reserves;
    }
    
 
    
    // Override
    
    @Override
    public String toString(){
        return "Client: {\nNom: " + this.nom + "\nCognom: " + this.cognom + "\nDNI: " + this.dni + "\nTel: " + this.telefon + "\nEmail: " + this.email + "\nSexe: " + this.sexe + "\nData de neixement: " + this.dataNeixement + "\nEdat: " + this.edat + "\nUsuari: " + this.usuari + "\nPass: ******\nCompte Bancari: " + this.compteBancari + "\nCondició: " + this.condicio + "\nComunicació comercial: " + this.comunicacioComercial + "\n}";
    }
    
}
