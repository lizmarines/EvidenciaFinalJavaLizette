
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Login {
     HashMap<String, String> users = new HashMap<>();

    public void Login(){ }

    public void loadUsers(){

        String filename = "/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/user.txt";
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String user, username, pass;
           
            while ((user = bufferedReader.readLine()) != null) {
                username = user.substring(0, user.indexOf(','));
                pass = user.substring(user.indexOf(',')+1);

                this.users.put(username, pass);
            }
        } 
        catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } 
        finally {
            
            try {
                
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } 
            catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }

    public User checkUser(String username, String password){
       
        User loggedUser = new User();

        users.entrySet().forEach(entry->{
            if(username.equals(entry.getKey()) && password.equals(entry.getValue())){
                loggedUser.login(username, password);
            }
        });

        return loggedUser;
    }
}

