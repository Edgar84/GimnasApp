
package gimnasapp;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gimnas {
    private String nom;
    private String CIF;
    private String telefon;
    private ArrayList<Client> clients;
    private ArrayList<Activitat> activitats;
    
    Scanner teclat = new Scanner(System.in);
    boolean sortir = false;
    
    // Constructor
    public Gimnas(String nom, String CIF, String telefon) {
        this.nom = nom;
        this.CIF = CIF;
        this.telefon = telefon;
        this.clients = new ArrayList<>();
    }
    
    // Functions
    public void gestionarGimnas() throws SQLException, NoSuchAlgorithmException{
        
        do {
            System.out.println("|**************MENU GIMNAS**************|");
            System.out.println("|                                       |");
            System.out.println("|           1.Gestió client             |");
            System.out.println("|          2.Veure activitats           |");
            System.out.println("|              3.Sortir                 |");
            System.out.println("|                                       |");
            System.out.println("|***************************************|");
            
            int opcioMenu = teclat.nextInt();

            switch (opcioMenu){
                case 1:
                    subMenuGestioClients();
                    break;
                case 2:
                    Activitat a = new Activitat();
                    a.veureActivitats();
                    visualitzarActivitats();
                    break;
                case 3:
                    sortir = true;
                    break;
                default:
                    sortir = true;
                    break;
            }
        }while(!sortir);
    }
    
    private void subMenuGestioClients() throws SQLException, NoSuchAlgorithmException{
        
        do {
            System.out.println("|*************GESTIÓ CLIENTS************|");
            System.out.println("|                                       |");
            System.out.println("|           1.Alta nou client           |");
            System.out.println("|         2.Visualitzar clients         |");
            System.out.println("|          3.Consultar clients          |");
            System.out.println("|            4.Baixa clients            |");
            System.out.println("|               5.Tornar                |");
            System.out.println("|                                       |");
            System.out.println("|***************************************|");
            
            int opcioMenu = teclat.nextInt();
            Client c = new Client();
            switch (opcioMenu){
                case 1:
                    c.altaClient();
                    break;
                case 2:
                    subMenuVisualitzarClients();
                    break;
                case 3:
                    c.consultarClients();
                    break;
                case 4:
                    c.baixaClient();
                    break;
                case 5:
                    gestionarGimnas();
                    break;
                default:
                    sortir = true;
                    break;
            }
        }while(!sortir);
    }
    
    private void subMenuVisualitzarClients() throws SQLException, NoSuchAlgorithmException{
        
        do {
            System.out.println("|**********VISUALITZAR CLIENTS**********|");
            System.out.println("|                                       |");
            System.out.println("|         1.Ordenat per cognoms         |");
            System.out.println("|          2.Ordenat per edat           |");
            System.out.println("|   3.Ordenat per més reserves fetes    |");
            System.out.println("|               4.Tornar                |");
            System.out.println("|                                       |");
            System.out.println("|***************************************|");
            
            int opcioMenu = teclat.nextInt();
            Client c = new Client();

            switch (opcioMenu){
                case 1:
                    this.clients = c.ordenarPerCognom();
                    visualitzarClients();
                    break;
                case 2:
                    this.clients = c.ordenarPerEdat();
                    visualitzarClients();
                    break;
                case 3:
                    this.clients = c.ordenarPerReserves();
                    visualitzarClients();
                    break;
                case 4:
                    subMenuGestioClients();
                    break;
                default:
                    sortir = true;
                    break;
            }
        }while(!sortir);
    }
    
    private void visualitzarClients(){
        for (Client client : clients) {
            System.out.println(client);
            System.out.println("--------------");
        }
    }
    
    private void visualitzarActivitats() {
        for (Activitat activitat : activitats) {
            System.out.println(activitat);
            System.out.println("--------------");
        }
    }
    
}
