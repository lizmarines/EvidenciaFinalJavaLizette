import java.util.*;
import java.io.*;
import java.util.Scanner;


public class AgendaCitas {
    private static final int doctor = 0;
    private static final int paciente = 1;

    public static void main(String[] args) {
       Scanner entrada = new Scanner(System.in);

        login(entrada);

    }

    public static void login(Scanner entrada){
        
        String username, password;
        boolean notLogged = true;

        Login x = new Login();

        while (notLogged){
            
            
            System.out.println("======================================");
            System.out.println("Bienvenido al sistema de Citas Medicas");
            System.out.println("======================================");
            System.out.println("Escriba el nombre de usuario");
            username = entrada.next();
            System.out.println("Escriba la contraseña:");
            password = entrada.next();

            x.loadUsers();
            
            if(x.checkUser(username, password).sesionIniciada){
                System.out.println("**************************************");
                System.out.println("Bienvenido " + username  );
                System.out.println("**************************************");
                notLogged = false;
                mostrarMenu(entrada);
            }
            
            else {
                System.out.println("Datos ingresados incorrectos, vuelva a intentarlo");
            }
        }
    }

    public static void mostrarMenu(Scanner entrada){
        boolean actMenu = true;
       
        String x, nombre, apellido, especialidad;
        int choiceDoc = 0, choicePat = 0, lengthDocs, lengthPats;

        BaseDeDatos bd = new BaseDeDatos();

        bd.load(doctor);
        bd.load(paciente);

        while (actMenu){


            System.out.println("Escriba el numero de opción que desees realizar");
                    System.out.println(" a) Crear un doctor-Escriba '1'    b) Crear un paciente-Escriba '2'      c) Crear una cita-Escriba '3'     d) Salir-Escriba '4' ");
                     x = entrada.next();

            switch (x){
                case "1":
                    Doctor doc = new Doctor();

                    System.out.println("Escriba el nombre del doctor");
                    nombre = entrada.next();
                    System.out.println("Escriba el apellido del doctor");
                    apellido = entrada.next();
                    System.out.println("Escriba la especialidad del doctor");
                    especialidad = entrada.next();

                    doc.addDoctor(nombre, apellido, especialidad, bd);
                    System.out.println("El doctor " + nombre + " " + apellido + " fue creado exitosamente");
                    break;
                case "2":
                    Paciente pat = new Paciente();
                    System.out.println("Escriba el nombre del paciente");
                    nombre = entrada.next();
                    System.out.println("Escriba el apellido del paciente");
                    apellido = entrada.next();

                    pat.addPaciente(nombre, apellido, bd);
                    System.out.println("El paciente " + nombre + " " + apellido + " fue creado exitosamente");
                    break;
                case "3":
                    System.out.println("Escriba el número de la lista de doctor que deseas agendar");
                    lengthDocs = bd.list(doctor);

                    boolean isValid = false;
                    String fecha, hora;

                    if(lengthDocs > 0){

                        while (!isValid){
                            choiceDoc = entrada.nextInt();
                            if(choiceDoc > lengthDocs || choiceDoc < 1) {
                                System.out.println("Ingresa un valor válido");
                            
                            }
                            
                            else
                            {
                                isValid = true;
                            }
                        }
                    }
                    else{
                        System.out.println("No hay suficientes doctores registrados para agendar una cita.");
                        break;
                    }

                    System.out.println("Ingresa el número del Paciente que acudirá a la cita");

                    isValid = false;
                    lengthPats = bd.list(paciente);

                    if(lengthPats > 0){

                        while (!isValid){
                            choicePat = entrada.nextInt();
                            if(choicePat > lengthDocs || choicePat < 1) 
                            {
                                System.out.println("Ingresa un valor válido");
                            }else{
                                isValid = true;
                            }
                        }
                    }
                    
                    else{
                        System.out.println("No hay suficientes paciente registrados para agendar una cita.");
                        break;
                    }

                    System.out.println("Ingresa la fecha de la cita:");
                    fecha = entrada.next();

                    System.out.println("Ingresa la hora de la cita:");
                    hora = entrada.next();

                    Doctor docCita = bd.getDoctor(choiceDoc);
                    Paciente patCita = bd.getPatient(choicePat);

                    Citas cita = new Citas();
                    cita.crearCita(docCita, patCita, fecha, hora);



                    break;

                case "4":
                    System.out.println("Nos vemos pronto :)");
                    actMenu = false;
                    break;
                default:
                    System.out.println("Solo puedes ingresar 1,2,3 o 4");
                    break;
            }
        }
    }
}
        

    
    
    /*
    public static void main(String[] args) {
      Scanner menuPrincipal = new Scanner(System.in);
        //int opcion;
        
        System.out.println("Bienvenida, favor elige una opción del siguiente menu:");
        System.out.println("Ingrese el numero de la opción deseada");
        System.out.println("1-Crear una cita ");
        System.out.println("2-Dar de alta un nuevo doctor");
        System.out.println("3-Dar de alta un Paciente");
        System.out.println("4-Ver lista de doctores");
        System.out.println("5-Ver lista de Patients");
        System.out.println("6-Ver lista de citas");
        System.out.println("7-Salir");
        // Hacer Excepción de ingresar valor que no esta en las opciones

        switch (menuPrincipal.nextInt()){
    
        case (1):
            System.out.println("Usted a elegido realizar una nueva cita"); 
            break;
        case (2):
            System.out.println("Usted a elegido dar de alta a un doctor ");
            break;
        case (3):
            System.out.println("Usted a elegido dar de alta a un Paciente");
            break;
        case (4):
            System.out.println("Usted a elegido ver el registro de citas");
            break;
        case (5):
            System.out.println("Usted a elegido ver el registro de doctores"); 
            break;
        case (6):
            System.out.println("Usted a elegido ver el registro de Patients");
            break;
        case (7):
            System.out.println("Usted a elegido salir");
            break;
           
        }
        }
}
        */


