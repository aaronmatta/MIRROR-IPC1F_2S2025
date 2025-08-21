package org.aaronmatta.system;

import java.util.Scanner;

public class Principal {

    //___ DECLARACION DE VARIABLES, ARRAYS, ETC. ___
    
    Scanner scanner = new Scanner(System.in);
       
    String nombre, categoria;
    double precio;
    int opcion, cantidadStock, codigoUnico;
    int ultimoCodigo=-1;
        
    String[][] inventario = new String[100][5];
    String[][] ventas = new String[100][4];
    String[][] bitacora = new String[100][4];
    
    //______________________________________________
    
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.generarEspaciosVacios();
//        programa.agregarDatosPrueba();
        programa.menu();
        
    }

    public void menu(){
        do{
            System.out.println("\n+----------------------------------------+");
            System.out.println("|             MENÚ PRINCIPAL             |");
            System.out.println("+----------------------------------------+");
            System.out.println("|    1. Agregar Producto.                |");
            System.out.println("|    2. Buscar Producto.                 |");
            System.out.println("|    3. Eliminar Producto.               |");
            System.out.println("|    4. Registrar Venta.                 |");
            System.out.println("|    5. Generar Reportes.                |");
            System.out.println("|    6. Ver Datos del Estudiante.        |");
            System.out.println("|    7. Bitácora.                        |");
            System.out.println("|    8. Salir.                           |");
            System.out.println("+----------------------------------------+");
            opcion = ingresarEntero("*    Elige una opcion (1-8): ");
            switch(opcion){
                case 1:
                    System.out.println("\n+----------------------------------------+");
                    System.out.println("|            AGREGAR PRODUCTO            |");
                    System.out.println("+----------------------------------------+");
                    nombre = ingresarTexto("*    Nombre: ");
                    categoria = ingresarTexto("*    Categoría: ");
                    precio = ingresarDecimal("*    Precio: ");
                    cantidadStock = ingresarEntero("*    Cantidad en Stock: ");
                    agregarProducto(nombre, categoria, precio, cantidadStock);
                    break;
                case 2:
                    do{
                        System.out.println("\n+----------------------------------------+");
                        System.out.println("|            BUSCAR  PRODUCTO            |");
                        System.out.println("+----------------------------------------+");
                        System.out.println("|    1. Buscar por código.               |");
                        System.out.println("|    2. Buscar por nombre.               |");
                        System.out.println("|    3. Buscar por categoría.            |");
                        System.out.println("|    4. Regresar         .               |");
                        System.out.println("+----------------------------------------+");
                        opcion = ingresarEntero("*    Elige una opcion (1-4): ");
                        switch(opcion){
                            case 1:
                                System.out.println("\n+----------------------------------------+");
                                System.out.println("|        BUSCAR PRODUCTO (CÓDIGO)        |");
                                System.out.println("+----------------------------------------+");
                                codigoUnico = ingresarEntero("*    Código: ");
                                System.out.println("+----------------------------------------+");
                                verProducto(opcion,String.valueOf(codigoUnico));
                                break;
                            case 2:
                                System.out.println("\n+----------------------------------------+");
                                System.out.println("|        BUSCAR PRODUCTO (NOMBRE)        |");
                                System.out.println("+----------------------------------------+");
                                nombre = ingresarTexto("*    Nombre: ");
                                System.out.println("+----------------------------------------+");
                                verProducto(opcion,nombre);
                                break;
                            case 3:
                                System.out.println("\n+----------------------------------------+");
                                System.out.println("|       BUSCAR PRODUCTO (CATEGORÍA)      |");
                                System.out.println("+----------------------------------------+");
                                categoria = ingresarTexto("*    Categoría: ");
                                System.out.println("+----------------------------------------+");
                                verProducto(opcion,categoria);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("\n[?]    INGRESE UNA DE LAS OPCIONES VÁLIDAS.");
                                break;
                        }
                        
                    }while(opcion!=4);
                    break;
                case 3:
                    System.out.println("\n+----------------------------------------+");
                    System.out.println("|           ELIMINAR  PRODUCTO           |");
                    System.out.println("+----------------------------------------+");
                    codigoUnico = ingresarEntero("*    Código: ");
                    eliminarProducto(codigoUnico);
                    break;
                case 4:
                    System.out.println("\n+----------------------------------------+");
                    System.out.println("|            REGISTRAR  VENTA            |");
                    System.out.println("+----------------------------------------+");
                    
                    break;
                case 5:
                    break;
                case 6:
                    mostrarDatosEstudiante();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 11: //SOLAMENTE PARA PRUEBAS ESTO SE ELIMINARA DESPUES
                    verListadoProductos();
                    break;
                default:
                    System.out.println("\n[?]    INGRESE UNA DE LAS OPCIONES VÁLIDAS.");
                    break;
            }
        }while(opcion!=8);
        
    }
    
    public void eliminarProducto(int codigo){
        boolean encontrado = false;
        for(int fila=0;fila<100;fila++){
            if(inventario[fila][0].equals(String.valueOf(codigo))){
                
                System.out.println("+----------------------------------------+");
                System.out.println("[✓]    ESTAS SEGURO DE ELIMINAR EL");
                System.out.println("       PRODUCTO "+inventario[fila][1]+", ");
                System.out.println("       CON CÓDIGO "+inventario[fila][0]+" ?");
                System.out.println("[X]    VUELVE A ESCRIBIR SU CODIGO");
                System.out.print("       PARA CONFIRMAR: ");
                codigoUnico = ingresarEntero("");
                
                if(inventario[fila][0].equals(String.valueOf(codigoUnico))){
                    
                    inventario[fila][0] = "-100";
                    inventario[fila][1] = "VACIO";
                    inventario[fila][2] = "VACIO";
                    inventario[fila][3] = "VACIO";
                    inventario[fila][4] = "VACIO";
                    
                    ordenarProductos();
                    
                    encontrado=true;
                    System.out.println("+----------------------------------------+");
                    System.out.println("[-]    PRODUCTO ELIMINADO EXITOSAMENTE.");
                    break;
                }
                
                System.out.println("+----------------------------------------+");
                System.out.println("[X]    ACCION CANCELADA.");
                return;
                
            }
        }
        if(!encontrado){
            System.out.println("+----------------------------------------+");
            System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO");
            System.out.println("      CON ESE CÓDIGO.");
            return;
        }
        
    }
    
    public void ordenarProductos(){
        String[][] nuevoInventario = new String[100][5];
        int contador = 0;
        for(int fila=0;fila<100;fila++){
            if(!(inventario[fila][0].equals("-100"))){
                nuevoInventario[contador][0] = inventario[fila][0];
                nuevoInventario[contador][1] = inventario[fila][1];
                nuevoInventario[contador][2] = inventario[fila][2];
                nuevoInventario[contador][3] = inventario[fila][3];
                nuevoInventario[contador][4] = inventario[fila][4];
                contador++;
            }
        }
        for(int fila=contador;fila<100;fila++){
            nuevoInventario[fila][0] = "-100";
            nuevoInventario[fila][1] = "VACIO";
            nuevoInventario[fila][2] = "VACIO";
            nuevoInventario[fila][3] = "VACIO";
            nuevoInventario[fila][4] = "VACIO";
        }
        for(int fila=0;fila<100;fila++){
            inventario[fila][0] = nuevoInventario[fila][0];
            inventario[fila][1]=nuevoInventario[fila][1];
            inventario[fila][2]=nuevoInventario[fila][2];
            inventario[fila][3]=nuevoInventario[fila][3];
            inventario[fila][4]=nuevoInventario[fila][4];
        }
        
    }
    
    public void buscarProducto(int opcion, String textoIngresado){
        
    }
    
    public void verProducto(int opcion, String textoIngresado){
        int contador = 0;
        switch(opcion){
            case 1:
                for (int fila=0;fila<100;fila++){
                    if(inventario[fila][0].equals(String.valueOf(textoIngresado))){ //Si el CODIGO es encontrado muestra los datos en esa posicion
                        System.out.println("-    Código: "+inventario[fila][0]);
                        System.out.println("-    Nombre: "+inventario[fila][1]);
                        System.out.println("-    Categoría: "+inventario[fila][2]);
                        System.out.println("-    Precio: "+inventario[fila][3]);
                        System.out.println("-    Stock: "+inventario[fila][4]);
                        System.out.println("+----------------------------------------+");
                        contador++;
                        break;
                    }
                }
                if(contador==0){
                    System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO.");
                    break;
                }
            case 2:
                for (int fila=0;fila<100;fila++){
                    if(inventario[fila][1].equals(String.valueOf(textoIngresado))){ //Si el NOMBRE coincide muestra los datos en esa posicion
                        System.out.println("-    Código: "+inventario[fila][0]);
                        System.out.println("-    Nombre: "+inventario[fila][1]);
                        System.out.println("-    Categoría: "+inventario[fila][2]);
                        System.out.println("-    Precio: "+inventario[fila][3]);
                        System.out.println("-    Stock: "+inventario[fila][4]);
                        System.out.println("+----------------------------------------+");
                        contador++;
                    }
                }
                if(contador==0){
                    System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO.");
                    break;
                }
            case 3:
                for (int fila=0;fila<100;fila++){
                    if(inventario[fila][2].equals(String.valueOf(textoIngresado))){ //Si la CATEGORIA coincide muestra los datos en esa posicion
                        System.out.println("-    Código: "+inventario[fila][0]);
                        System.out.println("-    Nombre: "+inventario[fila][1]);
                        System.out.println("-    Categoría: "+inventario[fila][2]);
                        System.out.println("-    Precio: "+inventario[fila][3]);
                        System.out.println("-    Stock: "+inventario[fila][4]);
                        System.out.println("+----------------------------------------+");
                        contador++;
                    }
                }
                if(contador==0){
                    System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO.");
                    break;
                }
        }
    }

    public void agregarProducto(String nombre, String categoria, double precio, int cantidadStock){
        
        boolean espacioDisponible = false;
        for(int fila=0;fila<100;fila++){
            if(inventario[fila][0].equals("-100")){
                espacioDisponible=true;
                break;
            }
        }
        
        if(!espacioDisponible){
            System.out.println("+----------------------------------------+");
            System.out.println("[?]   INVENTARIO LLENO.");
            return;
        }
        
        double confirmarPrecio = validarPositivo(1,precio);
        int confirmarStock = (int) validarPositivo(2,cantidadStock);
        codigoUnico = generarCodigoUnico();
        
        if( confirmarPrecio != -100 && confirmarStock != -100){
            for(int fila=0;fila<100;fila++){
                if(inventario[fila][0].equals("-100")){
                    inventario[fila][0] = String.valueOf(codigoUnico);
                    inventario[fila][1] = nombre;
                    inventario[fila][2] = categoria;
                    inventario[fila][3] = String.valueOf(precio);
                    inventario[fila][4] = String.valueOf(cantidadStock);
                    System.out.println("+----------------------------------------+");
                    System.out.println("[+]    PRODUCTO AGREGADO EXITOSAMENTE.");
                    System.out.println("       CON CÓDIGO: "+codigoUnico);
                    break;
                }
            }
        }
        
    }
    
    public int generarCodigoUnico(){
        return ++ultimoCodigo;
    }
    
    public void mostrarDatosEstudiante(){
        System.out.println("\n+----------------------------------------+");
        System.out.println("|          DATOS DEL ESTUDIANTE          |");
        System.out.println("+----------------------------------------+");
        System.out.println("|    Nombre: Gerson Aarón Matta Aguilar  |");
        System.out.println("|    Carnet: 202403711                   |");
        System.out.println("|    Curso: Introducción a la            |");
        System.out.println("|           Programacion y Computacion 1 |");
        System.out.println("|    Sección: F                          |");
        System.out.println("+----------------------------------------+");
    }
    
    public double validarPositivo(int mensaje, double numeroIngresado){
        if(numeroIngresado>=0){
            
            return numeroIngresado;   
            
        }else{
            if(mensaje==1){
                System.out.println("[?]   EL PRECIO NO PUEDE SER NEGATIVO.");
            }
            if(mensaje==2){
                System.out.println("[?]   EL STOCK NO PUEDE SER NEGATIVO.");
            }
            if(mensaje==3){
                System.out.println("[?]   EL NÚMERO NO PUEDE SER NEGATIVO.");
            }
        }
        return -100;
    }
    
    public int ingresarEntero(String mensaje) {
        int num = 0;
        boolean valido = false;

        do {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine();
                num = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("[?]    ERROR: Debe ingresar un número entero válido. Porfavor ingresar un número válido.");
            }
        } while (!valido);

        return num;
    }
    
    public String ingresarTexto(String mensaje) {
        String texto = "";
        boolean valido = false;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (!texto.isEmpty() && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                valido = true;
            } else {
                System.out.println("[?]    ERROR: Solo se permiten letras. Porfavor ingresar un texto válido: ");
            }
        } while (!valido);

        return texto;
    }
    
    public double ingresarDecimal(String mensaje) {
        double decimal = 0;
        boolean valido = false;

        do {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine();
                decimal = Double.parseDouble(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("[?]    ERROR: Debe ingresar un decimal válido. Porfavor ingresar un número válido.");
            }
        } while (!valido);

        return decimal;
    }
    
    public void generarEspaciosVacios(){
        for (int fila=0;fila<100;fila++){
            inventario[fila][0] = "-100";
            inventario[fila][1] = String.valueOf("VACIO");
            for (int col=2;col<5;col++){ //Empieza en 2, porque la seccion de ID y Nombre ya la modifico arriba.
                inventario[fila][col] = "VACIO";
            }
        }
    }
    
    public void agregarDatosPrueba(){ //FUNCION SOLO PARA TRABAJAR CON PRUEBAS
        
        for (int fila=0;fila<100;fila++){
            inventario[fila][0] = String.valueOf(fila);
            inventario[fila][1] = String.valueOf("Nombre"+(fila));
            for (int col=2;col<5;col++){ //Empieza en 2, porque la seccion de ID y Nombre ya la modifico arriba.
                inventario[fila][col] = "XX";
            }
        }
        
//        for (int fila=25;fila<100;fila++){
//            inventario[fila][0] = "-100";
//            inventario[fila][1] = String.valueOf("VACIO");
//            for (int col=2;col<5;col++){ //Empieza en 2, porque la seccion de ID y Nombre ya la modifico arriba.
//                inventario[fila][col] = "VACIO";
//            }
//        }
        
        inventario[6][0]="6";
        inventario[6][1]="NombreUnico";
        
//        inventario[7][0]="-100";
//        inventario[7][1]="VACIO";
//        
//        inventario[13][0]="-100";
//        inventario[13][1]="VACIO";
//        
//        inventario[20][0]="-100";
//        inventario[20][1]="VACIO";
//        
//        inventario[8][1]="NombreDiferente";
        
    }
    
    public void verListadoProductos(){ //FUNCION SOLO PARA TRABAJAR CON PRUEBAS
        int contarPersonajes = 0;
        System.out.println("+-----+-------------------------------------+----------------------------------------+-----------+-------------+");
        System.out.println("| ID  | NOMBRE                              | CATEGORIA                              | PRECIO    | STOCK       |");
        System.out.println("+-----+-------------------------------------+----------------------------------------+-----------+-------------+");
        for (int fila=0;fila<100;fila++){
//            if(!(inventario[fila][0].equals("100"))){ //No se muestran las IDS 100, o sea las que no contienen datos.
                System.out.printf("| %-4s | %-20.20s | %-20.20s | %-48.48s | %-5s |%n", 
                    inventario[fila][0],
                    inventario[fila][1],
                    inventario[fila][2],
                    inventario[fila][3],
                    inventario[fila][4]
                
                );
                contarPersonajes++;
//            }
        }
        if(contarPersonajes == 0){
            System.out.println("+-----+----------------------+----------------------+--------------------------------------------------+-------+");
            System.out.println("NO HAY PRODUCTOS AGREGADOS.");
        }else{
            System.out.println("+-----+----------------------+----------------------+--------------------------------------------------+-------+");
            System.out.println("Productos agregados: ["+contarPersonajes+"/100]");
        }
    }
}
