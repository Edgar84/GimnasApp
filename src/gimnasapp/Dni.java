
package gimnasapp;

public class Dni {
    
    private String num;

    public Dni(String num) {
        this.num = num;
    }
    public Dni() {
    }

    public void setNum(String num) {
        if(validarDni(num)){
            this.num = num;
        }else{
            System.out.println("DNI incorrecte");
        }
        
    }

    public String getNum() {
        return num;
    }
    
    
    
    public boolean validarDni(String num){
        
        boolean esValid = false;
        
        if(num.length() == 9){
            for( int i = 0; i < num.substring(8).length(); i++ ){
                if(!Character.isDigit(String.valueOf(num.substring(8).length()).charAt(i))){
                    return false;
                }else{
                    esValid = true;
                }
            }
        }else{
            return false;
        }
        if(esValid){
            int numDni = Integer.parseInt(num.substring(0, 8));
            String[] arrayLletra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E",};

            if((num.charAt(8) >= 'a' && num.charAt(8) <= 'z') || (num.charAt(8) >= 'A' && num.charAt(8) <= 'Z')){
                String lletra = num.substring(8).toUpperCase();
                int reste = numDni % 23;

                if(!arrayLletra[reste].equals(lletra)){
                    return false;
                }
                return  true;
            }
        }
        
        return false;
    }
    
    
    
}
