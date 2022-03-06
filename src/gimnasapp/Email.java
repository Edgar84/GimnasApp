
package gimnasapp;

public class Email {
    
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public Email() {
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getEmail() {
        return email;
    }
    
    public boolean validarEmail(String email){
        return email.contains("@") && email.substring(email.indexOf("@")).contains(".");
    }
    
    // Override

    @Override
    public String toString() {
        return this.getEmail();
    }
    
   
}
