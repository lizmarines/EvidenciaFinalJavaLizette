
import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseDeDatos {
    
    HashMap<String, String[]> doctors = new HashMap<>();
    HashMap<String, String[]> pacientes = new HashMap<>();

    String filename;
    
    BufferedWriter bufferedWriter = null;

    
    private static final int doctor = 0;
    private static final int paciente = 1;

    public void BaseDeDatos()
    {
        this.filename = "";
    }

    public String getItemId(int type){
        String newId;
        int auxId;

        if(type == doctor){
            this.filename = "/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/doctores.csv";
            auxId = doctors.size();
        }else{
            this.filename = "/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/pacientes.csv";
            auxId = pacientes.size();
        }

        auxId++;
        newId = String.valueOf(auxId);

        return newId;
    }

    public void addItem(int type, String id, String[] info){
        if (type == doctor){
            doctors.put(id, info);
            save(doctor);
        }else{
            pacientes.put(id, info);
            save(paciente);
        }
    }

    public void load(int type){

        if(type == doctor){
            this.filename = "/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/doctores.csv";
        }else{
            this.filename = "/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/pacientes.csv";
        }

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(this.filename));

            String line, id, name, lastName, specialty = "";


            while ((line = bufferedReader.readLine()) != null) {

                String[] res = line.split("[,]", 0);

                if(res.length > 1)
                {
                    id = res[0];
                    name = res[1];
                    lastName = res[2];

                    if(res.length > 3)
                    
                    {specialty = res[3];}

                    if("/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/doctores.csv".equals(this.filename))
                    
                    {
                        String[] info = {name, lastName, specialty};
                        doctors.put(id, info);
                    }
                    else
                    
                    {
                        String[] info = {name, lastName};
                        pacientes.put(id, info);
                    }
                }
            }
        } catch(IOException e) {
                    System.out.println("IOException catched while reading: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (bufferedReader != null) 
                {
                    bufferedReader.close();
                } 
            } 
            
            catch (IOException e) {
                
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public void save(int type){

        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.filename));

            if(type == doctor){
                doctors.entrySet().forEach(entry->{
                    String line;
                    line = entry.getKey() + ",";
                    for (int i = 0; i < entry.getValue().length; i++) {
                        line = line + entry.getValue()[i];
                        if(i != entry.getValue().length-1){
                            line = line + ",";
                        }
                    }
                    try {
                        this.bufferedWriter.write(line + "\n");
                    } 
                    catch (IOException e) {
                    }
                });
            }else{
                pacientes.entrySet().forEach(entry->{
                    String line;
                    line = entry.getKey() + ",";
                    for (int i = 0; i < entry.getValue().length; i++) {
                        line = line + entry.getValue()[i];
                        if(i != entry.getValue().length-1){
                            line = line + ",";
                        }
                    }
                    try {
                        this.bufferedWriter.write(line + "\n");
                    } 
                    catch (IOException e) {
                    }
                });
            }

        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } 
        finally {
            
            try {
                if (this.bufferedWriter != null) {
                    this.bufferedWriter.close();
                }
            } 
            catch (IOException e) {
                    System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public int list(int type){

        int cont = 0;

        if(type == doctor){
            doctors.entrySet().forEach(entry-> {
                String line;
                line = entry.getKey() + ") ";
                for (int i = 0; i < entry.getValue().length; i++) {
                    line = line + entry.getValue()[i] + " ";
                        if(i == entry.getValue().length-2){
                            line = line + " - ";
                        }
                }
                        System.out.println(line);
            });
            cont = doctors.size();
        }else{
            pacientes.entrySet().forEach(entry-> {
                String line;
                line = entry.getKey() + ")";
                for (int i = 0; i < entry.getValue().length; i++) {
                    line = line + entry.getValue()[i] + " ";
                }

                System.out.println(line);
            });
            cont = pacientes.size();
        }

        return cont;

    }

    public Doctor getDoctor(int auxKey){
        String key, name, lastName, specialty;
        String docInfo[] = new String[3];

        key = String.valueOf(auxKey);
        docInfo = doctors.get(key);

        name = docInfo[0];
        lastName = docInfo[1];
        specialty = docInfo[2];

        Doctor x = new Doctor();
        x.setDoctor(key, name, lastName, specialty);

        return x;
    }

    public Paciente getPatient(int auxKey){
        String key, name, lastName;
        String patInfo[] = new String[2];

        key = String.valueOf(auxKey);
        patInfo = pacientes.get(key);

        name = patInfo[0];
        lastName = patInfo[1];

        Paciente x = new Paciente();
        x.setPaciente(key, name, lastName);

        return x;
    }

}
