import java.io.*;
import java.util.HashMap;

public class Citas {
     Doctor doctor = new Doctor();
    Paciente Patient = new Paciente();
    String id;
    String fecha;
    String hora;
    
    
    HashMap<String, String[]> citas = new HashMap<>();

    BufferedWriter bufferedWriter;

    public void Citas(){
        this.fecha = "";
        this.hora = "";
    }

    public void crearCita(Doctor doc, Paciente pat, String date, String hour){
       
      load();

        int numID;
        numID = citas.size();
        numID++;

        this.id = String.valueOf(numID);
        this.doctor = doc;
        this.Patient = pat;
        this.fecha = date;
        this.hora = hour;

                String info[] = {this.doctor.id, this.Patient.id, this.fecha, this.hora};

                citas.put(this.id, info);
                save();

        
        
        System.out.println("************************************************");
        System.out.println("Reporte de Citas");
        System.out.println("La cita del Patient " + this.Patient.nombre + " " + this.Patient.apellido);
        System.out.println("Con el doctor " + this.doctor.nombre + " " + this.doctor.apellido);
        System.out.println("Fecha: " + this.fecha + " Hora: " + this.hora);
        System.out.println("Se realizo correctamente");
        System.out.println("************************************************");
        
    }

    public void load(){

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/citas.txt"));

            String line, id, id_doc, id_pat, fecha, hora;


            while ((line = bufferedReader.readLine()) != null) {

                String[] res = line.split("[,]", 0);

                if(res.length > 1){
                    id = res[0];
                    id_doc = res[1];
                    id_pat = res[2];
                    fecha = res[3];
                    hora = res[4];

                    String[] info = {id_doc, id_pat, fecha, hora};
                    citas.put(id, info);
                }
            }
        } catch(IOException e) {
         
            System.out.println("IOException catched while reading: " + e.getMessage());
        
        
        } finally {
            try {
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

    public void save(){

        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter("/Users/lizettemarines/NetBeansProjects/EvidenciaFinalJavaLizette/src/main/java/citas.txt"));

                citas.entrySet().forEach(entry->
                {
                    String line;
                    line = entry.getKey() + ",";
                    for (int i = 0; i < entry.getValue().length; i++) 
                    {
                        line = line + entry.getValue()[i];
                        if(i != entry.getValue().length-1)
                        {
                            line = line + ",";
                        }
                    }

                    try {
                        this.bufferedWriter.write(line + "\n");
                    } catch (IOException e) 
                    {
                    
                    }
                });



        } catch(IOException e) {
            
            
            System.out.println("IOException catched while reading: " + e.getMessage());
            
        } 
        
        finally 
        
        {
            try 
            
            {
                
                if (this.bufferedWriter != null)
                {
                    this.bufferedWriter.close();
               
                }
            }
            catch (IOException e) 
            
            {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }

}