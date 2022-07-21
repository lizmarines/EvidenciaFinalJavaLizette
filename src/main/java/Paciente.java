
public class Paciente {
    private static final int Paciente = 1;
    String id;
    String nombre;
    String apellido;

    public void Paciente(){
        this.id = "";
        this.nombre = "";
        this.apellido = "";
    }

    public void addPaciente(String name, String lastName, BaseDeDatos x){
        String[] info = new String[2];

        this.nombre = name;
        this.apellido = lastName;

        this.id = (x.getItemId(Paciente));

        info[0] = this.nombre;
        info[1] = this.apellido;

        x.addItem(Paciente, this.id, info);

    }

    public void setPaciente(String id, String name, String lastName){
        this.id = id;
        this.nombre = name;
        this.apellido = lastName;
    }
}




    /*
    private static final int Paciente =1;
    String id;
    String nombre;
    String apellido;

    public Paciente() {
        this.id = "";
        this.nombre = "";
        this.apellido = "";
    }
    
    public void addPaciente(String name, String lastName, BaseDatos x){
            String[] info = new String[2];
            
            this.nombre = name;
            this.apellido= lastName;
            
            this.id =(x.getItemId(Paciente));
            
            info[0]=this.nombre;
            info[1]=this.apellido;
            
            x.addItem(Paciente,this.id,info);
            
            }
    
    public void setPaciente(String id, String name, String lastName){
        this.id=id;
        this.nombre=name;
        this.apellido=lastName;
    }
}
*/