package org.aaronmatta.system;

import java.util.Scanner;

public class Principal {
    
    //___ DECLARACION DE VARIABLES, ARRAYS, ETC. ___
    
    Scanner scanner = new Scanner(System.in);
        
    int opcion;
    String id, nombre, arma, habilidades, nivel;
        
    String[][] personaje = new String[25][5];
    
    //______________________________________________
    
    public static void main(String[] args) {
        
        Principal programa = new Principal();
        programa.menu();
        
    }
    
    
   // ______________ MÉTODOS ______________
    
    public void menu(){
        vaciarDatosPersonajes();
//        agregarDatosPrueba();
        do{
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Agregar personaje.");
            System.out.println("2. Modificar personaje.");
            System.out.println("3. Eliminar personaje.");
            System.out.println("4. Ver datos de un personaje.");
            System.out.println("5. Ver listado de personajes.");
            System.out.println("6. Realizar pelea entre personajes.");
            System.out.println("7. Ver historial de peleas.");
            System.out.println("8. Ver datos de estudiante.");
            System.out.println("9. Salir");
            System.out.print("Elige una opcion (1-9): ");
            opcion = scanner.nextInt();
            
            switch(opcion){
                case 1:
                    System.out.println("\n--- AGREGAR PERSONAJE ---");
                    scanner.nextLine();
                    
                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();
                    
                    System.out.print("Arma: ");
                    arma = scanner.nextLine();
                    
                    System.out.print("Habilidades: ");
                    habilidades = scanner.nextLine();
                    
                    System.out.print("Nivel: ");
                    nivel = scanner.nextLine();
                    
                    agregarPersonaje(nombre,arma,habilidades,nivel);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("\n--- ELIMINAR PERSONAJE ---");
                    scanner.nextLine();
                    
                    System.out.print("Ingrese el ID del personaje a eliminar: ");
                    id = scanner.nextLine();
                    eliminarPersonaje(id);
                    break;
                case 4:
                    System.out.println("\n--- VER DATO DE UN PERSONAJE ---");
                    scanner.nextLine();
                    
                    System.out.print("Ingrese el ID del personaje a buscar: ");
                    id = scanner.nextLine();
                    verDatoPersonaje(id);
                    break;
                case 5:
                    verListadoPersonajes();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    mostrarDatosEstudiante();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Ingrese una de las opciones validas.");
                    break;
            }
        }while(opcion!=9);
        
    }
    
    public void verDatoPersonaje(String id){
        if(Integer.parseInt(id)>25){
            System.out.println("Ingrese un ID válido.");
        }else{
            
            int existeId = buscarId(id);
            
            if(existeId!=100){
                System.out.println("----------- DATOS -----------");
                System.out.println("ID: "+personaje[existeId][0]);
                System.out.println("Nombre: "+personaje[existeId][1]);
                System.out.println("Arma: "+personaje[existeId][2]);
                System.out.println("Habilidades: "+personaje[existeId][3]);
                System.out.println("Nivel: "+personaje[existeId][4]);
 
            }else{
                System.out.println("El ID de ese personaje no existe.");
            }
            
        }
        
    }
    
    public void eliminarPersonaje(String id){
        
        if(Integer.parseInt(id)>25){
            System.out.println("Ingrese un ID válido.");
        }else{
            
            int existeId = buscarId(id);
            
            if(existeId!=100){
                
                personaje[existeId][0]="100";
                personaje[existeId][1]="XX";
                personaje[existeId][2]="XX";
                personaje[existeId][3]="XX";
                personaje[existeId][4]="XX";
 
            }else{
                System.out.println("El ID de ese personaje no existe.");
            }
            
        }
        
        
    }
    
    public void agregarPersonaje(String nombre, String arma, String habilidades, String nivel){
        
        int posicion = validarIdUnico(id);
        boolean validarNombreUnico = validarNombreUnico(nombre);
        boolean validarNivel = validarNivel(nivel);
        
        if(posicion != 100 && validarNombreUnico==true && validarNivel== true){
            personaje[posicion][0] = String.valueOf(posicion+1);
            personaje[posicion][1] = nombre;
            personaje[posicion][2] = arma;
            personaje[posicion][3] = habilidades;
            personaje[posicion][4] = nivel;
        }
        
        if(posicion == 100){
            System.out.println("La cantidad de personajes agregados esta llena. (25/25). Borra algun personaje.");
        }
        
    }
    
    public int buscarId(String id){
        for (int fila=0;fila<25;fila++){
            if(personaje[fila][0].equals(id)){
                return fila;
            }
        }
        return 100;
    }
    
    public int validarIdUnico(String id){
        int contador=0;
        for (int fila=0;fila<25;fila++){
            
            contador=contador+1;
            System.out.println(personaje[fila][0]+"=100");
            System.out.println(personaje[fila][0].equals("100"));
            if(personaje[fila][0].equals("100")){ //Si es igual a 100, es porque está vacia.
                return fila;
            }
            
        }
        
        return 100;
    }
    
    public boolean validarNombreUnico(String nombre){
        for(int fila=0;fila<25;fila++){
            if(personaje[fila][1].equals(nombre)){ //Si el nombre que ingreso el usuario es igual a alguno en la lista le dará error.
                System.out.println("Este nombre ya esta en uso.");
                return false;
            }
        }
        return true;
    }
    
    public boolean validarNivel(String nivel){
        if(!(Integer.parseInt(nivel)>=1 && Integer.parseInt(nivel)<=100)){
            System.out.println("Ingrese un nivel valido (1-100).");
            return false;
        }
        
        return true;
    }
    
//    public void verListadoPersonajes(){
//        for (int fila=0;fila<25;fila++){
//            System.out.print(fila+".\t");
//            for (int col=0;col<5;col++){
//                System.out.print(personaje[fila][col]+ "\t");
//            }
//            System.out.println("");
//        }
//    }
    
    public void verListadoPersonajes(){
        int contarPersonajes = 0;
        System.out.println("+-----+----------------------+----------------------+---------------------------+-------+");
        System.out.println("| ID  | NOMBRE               | ARMA                 | HABILIDADES               | NIVEL |");
        System.out.println("+-----+----------------------+----------------------+---------------------------+-------+");
        for (int fila=0;fila<25;fila++){
            if(!(personaje[fila][0].equals("100"))){
                System.out.printf("| %-3s | %-20.20s | %-20.20s | %-25.25s | %-5s |%n", 
                    personaje[fila][0],
                    personaje[fila][1],
                    personaje[fila][2],
                    personaje[fila][3],
                    personaje[fila][4]
                
                );
                contarPersonajes++;
            }
        }
        if(contarPersonajes == 0){
            System.out.println("+-----+----------------------+----------------------+---------------------------+-------+");
            System.out.println("NO HAY PERSONAJES AGREGADOS.");
        }else{
            System.out.println("+-----+----------------------+----------------------+---------------------------+-------+");
            System.out.println("Personajes agregados: ["+contarPersonajes+"/25]");
        }
    }
    
    public void mostrarDatosEstudiante(){
        System.out.println("\n--- DATOS DEL ESTUDIANTE ---");
        System.out.println("Nombre: Gerson Aaron Matta Aguilar");
        System.out.println("Carnet: 202403711");
        System.out.println("Curso: Introduccion a la Programacion y Computacion 1");
        System.out.println("Seccion: F");
    }
    
    public void vaciarDatosPersonajes(){
        
        for (int fila=0;fila<25;fila++){
            personaje[fila][0] = "100";
            for (int col=1;col<5;col++){ //Empieza en 1, porque la seccion de ID ya la modifico arriba.
                personaje[fila][col] = "XX";
            }
        }   
        
    }
    
    public void agregarDatosPrueba(){
        
        for (int fila=0;fila<25;fila++){
            personaje[fila][0] = String.valueOf(fila+1);
            personaje[fila][1] = String.valueOf("Nombre"+(fila+1));
            for (int col=2;col<5;col++){ //Empieza en 2, porque la seccion de ID y Nombre ya la modifico arriba.
                personaje[fila][col] = "XX";
            }
        }
        
        personaje[7][0]="100";
        personaje[7][1]="XX";
        
        personaje[13][0]="100";
        personaje[13][1]="XX";
        
        personaje[20][0]="100";
        personaje[20][1]="XX";
        
        personaje[8][1]="Segnisasdasdasdsd";
        
    }
    
    
}
