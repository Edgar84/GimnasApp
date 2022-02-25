
package gimnasapp;

import java.time.LocalDate;
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
    private String compteBancari;
    private String condicio;
    private boolean comunicacioComercial;

    // Constructors
    public Client(String nom, String cognom, Dni dni, int telefon, Email email, String sexe, String usuari, String pass, String compteBancari, boolean comunicacioComercial) {
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
        this.telefon = telefon;
        this.email = email;
        this.sexe = sexe;
        //this.dataNeixement = dataNeixement;
        this.usuari = usuari;
        this.pass = pass;
        this.compteBancari = compteBancari;
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
        String nom = teclat.nextLine();
        
        System.out.println("Cognom:");
        String cognom = teclat.nextLine();
        
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
        
        String sexe = "";
        do {
            try {
                while (sexe.equals("")) {   
                    System.out.println("Sexe:");
                    System.out.println("1- Home");
                    System.out.println("2- Dona");
                    int opcioMenu = teclat.nextInt();
                    continua = true;

                    switch(opcioMenu){
                        case 1:
                            System.out.println("Home");
                            sexe = "H";
                        case 2:
                            System.out.println("Dona");
                            sexe = "D";
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
        
        System.out.println("Usuari:");
        String user = teclat.nextLine();
        
        System.out.println("Contrasenya:");
        String pass = teclat.nextLine();        
        
        String compteBancari = "";
        do{
            System.out.println("Compte bancari:");
            compteBancari = teclat.nextLine();

            while (!new CompteBancari(compteBancari).validarIBAN(compteBancari)) {                    
                System.out.println("El Compte bancari introduït no es correcte");
                compteBancari = teclat.nextLine();
            }
            continua = true;
        }while (!continua);
        
        /*****************************/
        System.out.println("Condició:");
        String condicio = teclat.nextLine();
        System.out.println("Comunicació comercial:");
        String comunicacioComercial = teclat.next();
    }
    
    private void mostrarclient(){
        
    }
    
    @Override
    public String toString(){
        return "Client {Nom:" + this.nom + ", Cognom: " + this.cognom + ", DNI: " + this.dni + ", Tel: " + this.telefon + ", Email: " + this.email + ", sexe: " + this.sexe + ", Usuari: " + this.usuari + ", Pass: ******, Compte Bancari: " + this.compteBancari + ", Condició: " + this.condicio + ", Comunicació comercial: " + this.comunicacioComercial;
    }
    
}
