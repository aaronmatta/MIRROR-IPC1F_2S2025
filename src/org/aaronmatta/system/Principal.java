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
    
    
   // MÉTODOS
    
    public void menu(){
        agregarDatosPrueba();
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
//                    scanner.nextLine();
//                    
//                    System.out.print("Nombre: ");
//                    nombre = scanner.nextLine();
//                    
//                    System.out.print("Arma: ");
//                    arma = scanner.nextLine();
//                    
//                    System.out.print("Habilidades: ");
//                    habilidades = scanner.nextLine();
//                    
//                    System.out.print("Nivel: ");
//                    nivel = scanner.nextLine();
                    
                    agregarPersonaje("1",nombre,arma,habilidades,nivel);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    verListadoPersonajes();
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                    break;
                default:
                    System.out.println("Ingrese una de las opciones validas.");
                    break;
            }
        }while(opcion!=9);
        
    }
    
    public void agregarPersonaje(String id, String nombre, String arma, String habilidades, String nivel){
        if(validarIdUnico(id)!=0){
            
        }else{
            System.out.println("La cantidad de personajes agregados está llena (25/25).");
        };
        
        
        
        
//        personaje[0][0] = id;
//        personaje[0][1] = nombre;
//        personaje[0][2] = arma;
//        personaje[0][3] = habilidades;
//        personaje[0][4] = nivel;
        
    }
    
    public int validarIdUnico(String id){
        int contador=0;
        for (int fila=0;fila<25;fila++){
            contador=contador+1;
            if(!(personaje[fila][0].equals(id))){
                return contador;
            }
        }
        
        return 0;
    }
    
    public void verListadoPersonajes(){
        for (int fila=0;fila<25;fila++){
            System.out.print(fila+".\t");
            for (int col=0;col<5;col++){
                System.out.print(personaje[fila][col]+ "\t");
            }
            System.out.println("");
        }
    }
    
    public void mostrarDatosEstudiante(){
        System.out.println("\n--- DATOS DEL ESTUDIANTE ---");
        System.out.println("Nombre: Gerson Aaron Matta Aguilar");
        System.out.println("Carnet: 202403711");
        System.out.println("Curso: Introduccion a la Programacion y Computacion 1");
        System.out.println("Seccion: F");
    }
    
    public void agregarDatosPrueba(){
        personaje[0][0] = "1";
        personaje[0][1] = "Nombre01";
        personaje[0][2] = "Arma01";
        personaje[0][3] = "Habilidades01";
        personaje[0][4] = "10";
        
        personaje[1][0] = "2";
        personaje[1][1] = "Nombre12";
        personaje[1][2] = "Arma12";
        personaje[1][3] = "Habilidades12";
        personaje[1][4] = "15";
        
        personaje[2][0] = "3";
        personaje[2][1] = "Nombre13";
        personaje[2][2] = "Arma13";
        personaje[2][3] = "Habilidades13";
        personaje[2][4] = "20";
        
        personaje[3][0] = "4";
        personaje[3][1] = "Nombre14";
        personaje[3][2] = "Arma14";
        personaje[3][3] = "Habilidades14";
        personaje[3][4] = "25";
        
        personaje[4][0] = "5";
        personaje[4][1] = "Nombre15";
        personaje[4][2] = "Arma15";
        personaje[4][3] = "Habilidades15";
        personaje[4][4] = "30";
        
        personaje[5][0] = "6";
        personaje[5][1] = "Nombre16";
        personaje[5][2] = "Arma16";
        personaje[5][3] = "Habilidades16";
        personaje[5][4] = "35";
    }
    
    
}
