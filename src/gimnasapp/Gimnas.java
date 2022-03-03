
package gimnasapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Gimnas {
    private String nom;
    private String CIF;
    private String telefon;
    ArrayList<Client> clients;
    
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
    public void gestionarGimnas(){
        
        
        do {
            System.out.println("|**************MENU GIMNAS**************|");
            System.out.println("|                                       |");
            System.out.println("|             Gestió client             |");
            System.out.println("|            Veure activitats           |");
            System.out.println("|                Sortir                 |");
            System.out.println("|                                       |");
            System.out.println("|***************************************|");
            
            int opcioMenu = teclat.nextInt();

            switch (opcioMenu){
                case 1:
                    subMenuGestioClients();
                    break;
                case 2:
                    veureActivitats();
                    break;
                case 3:
                    generarComandes();
                    break;
                case 4:
                    analitzarComandes();
                    break;
                case 5:
                    sortir = true;
                    break;
                default:
                    sortir = true;
                    break;
            }
        }while(!sortir);
    }
    
    private void subMenuGestioClients(){
        do {
            System.out.println("|*************GESTIÓ CLIENTS************|");
            System.out.println("|                                       |");
            System.out.println("|             Alta nou client           |");
            System.out.println("|           Llistat de clients          |");
            System.out.println("|                Tornar                 |");
            System.out.println("|                                       |");
            System.out.println("|***************************************|");
            
            int opcioMenu = teclat.nextInt();

            switch (opcioMenu){
                case 1:
                    Client c = new Client();
                    c.altaClient();
                    break;
                case 2:
                    Client d = new Client();
                    d.llistarClients();
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
    
}
