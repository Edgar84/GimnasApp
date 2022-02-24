
package gimnasapp;

import java.math.BigInteger;

public class CompteBancari {
    
    private String numCompte;

    public CompteBancari(String numCompte) {
        this.numCompte = numCompte;
    }
    
    public boolean validarIBAN(String numCompte){
        
        if(numCompte.length() == 24 && numCompte.substring(0, 1).toUpperCase().equals("E") && numCompte.substring(1, 2).toUpperCase().equals("S")){
            
            //String numcompleto = numCompte.substring(3) + "142800";
            
            BigInteger numcompleto = new BigInteger(numCompte.substring(3) + "142800");
            BigInteger numControl = numcompleto / 97;
            System.out.println(numcompleto);
            return false;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return this.numCompte;
    }
    
}
