
package gimnasapp;

public class Dni {
    
    private String num;

    public Dni(String num) {
        this.num = num;
    }
    
    public boolean validarDni(){
        
        if(this.num.length() == 9){
        
            int numDni = Integer.parseInt(this.num.substring(0, 8));
            String lletra = this.num.substring(8).toUpperCase();
            String[] arrayLletra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E",};
            int reste = numDni % 23;
            
            if(!arrayLletra[reste].equals(lletra)){
                return false;
            }
            
            return  true;
            
        }else {
            return false;
        }
        
        
    }
    
}
