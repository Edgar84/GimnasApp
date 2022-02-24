
package gimnasapp;

public class Email {
    
    private String email;

    public Email(String email) {
        this.email = email;
    }
    
    public boolean validarEmail(){
        return this.email.contains("@") && this.email.substring(this.email.indexOf("@")).contains(".");
    }
}
