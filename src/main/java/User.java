
public class User {
    String username;
    String password;
    boolean sesionIniciada;

    public void User(){
        this.username = "";
        this.password = "";
        this.sesionIniciada = false;
    }

    public void login(String user, String pw){
        this.username = user;
        this.password = pw;
        this.sesionIniciada = true;
    }
}



