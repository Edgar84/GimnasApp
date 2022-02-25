
package gimnasapp;

import java.math.BigInteger;

public class CompteBancari {
    
    private String numCompte;

    public CompteBancari(String numCompte) {
        this.numCompte = numCompte;
    }
    //public boolean validarIBAN(String numCompte){
    public boolean validarIBAN(String cuenta){
       /* 
        boolean esValid = false;
        int resto = 0;
        BigInteger control_1 = new BigInteger("97");
        String controlCode = "";
        
        // Comprovem si el num de compte te 24 dígits i si els dos primers son "ES"
        if(numCompte.length() == 24 && numCompte.substring(0, 1).toUpperCase().equals("E") && numCompte.substring(1, 2).toUpperCase().equals("S")){
            // Comprovem que el reste de caracters siguin números
            for( int i = 0; i < numCompte.substring(2).length(); i++ ){
                if(!Character.isDigit( numCompte.substring(2).charAt(i))){
                    return false;
                }else{
                    esValid = true;
                }
            }
            
            if(esValid){
                // Si el num de compte es correcte fem les operacions de comprovació
                BigInteger numcompleto = new BigInteger(numCompte.substring(2) + "142800");
                resto = numcompleto.mod(control_1).intValue();
                int codiControl = 98 - resto;
                controlCode = String.valueOf(codiControl);
                if(codiControl < 10){
                    controlCode = "0" + controlCode;
                }
                
                System.out.println(controlCode);
            
            }
            if(numCompte.substring(2,4).equals(controlCode)) {
                esValid = true;
                System.out.println("true: " + numCompte.substring(2));
            } else {
                esValid = false;
                System.out.println("false: " + numcompleto);
            }
            return false;
        }else{
            return false;
        }
*/
     ///////////////////////////////////////
     
     boolean esValido = false;
		int i = 2;
		int caracterASCII = 0; 
		int resto = 0;
		int dc = 0;
		String cadenaDc = "";
		BigInteger cuentaNumero = new BigInteger("0"); 
		BigInteger modo = new BigInteger("97");

		if(cuenta.length() == 24 && cuenta.substring(0,1).toUpperCase().equals("E") 
			&& cuenta.substring(1,2).toUpperCase().equals("S")) {

			do {
				caracterASCII = cuenta.codePointAt(i);
				esValido = (caracterASCII > 47 && caracterASCII < 58);
				i++;
			}
			while(i < cuenta.length() && esValido); 
		
		
			if(esValido) {
				cuentaNumero = new BigInteger(cuenta.substring(4,24) + "142800");
				resto = cuentaNumero.mod(modo).intValue();
				dc = 98 - resto;
				cadenaDc = String.valueOf(dc);
			}	
			
			if(dc < 10) {
				cadenaDc = "0" + cadenaDc;
			} 

			// Comparamos los caracteres 2 y 3 de la cuenta (dígito de control IBAN) con cadenaDc
			if(cuenta.substring(2,4).equals(cadenaDc)) {
				esValido = true;
                                System.out.println(cadenaDc);
                                System.out.println(cuenta.substring(2,4));
			} else {
				esValido = false;
			}
		}

		return false;
        
        
        
        
        
    }///////////////////////////////////////

    @Override
    public String toString() {
        return this.numCompte;
    }
    
}
