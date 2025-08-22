package org.aaronmatta.system;

import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Principal {

    //___ DECLARACION DE VARIABLES, ARRAYS, ETC. ___
    
    Scanner scanner = new Scanner(System.in);
       
    String nombre, categoria;
    double precio;
    int opcion, cantidadStock, codigoUnico, cantidad;
    int ultimoCodigoProducto=-1, ultimoCodigoVenta = -1;
        
    String[][] inventario = new String[100][5];
    String[][] ventas = new String[100][3];
    String[][] bitacora = new String[100][4];
    
    //______________________________________________
    
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.generarEspaciosVaciosInventario();
        programa.agregarDatosPrueba();
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
                    registrarVenta();
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
                case 22: //SOLAMENTE PARA PRUEBAS ESTO SE ELIMINARA DESPUES
                    verListadoVentas();
                    break;
                default:
                    System.out.println("\n[?]    INGRESE UNA DE LAS OPCIONES VÁLIDAS.");
                    break;
            }
        }while(opcion!=8);
        
    }
    
    public void registrarVenta() {
        
        String[][] carrito = new String[100][4];
        carrito = generarEspaciosVaciosCarrito(carrito);
        
        int contador = 0, existeId = 0;
        double total = 0.00, monto = 0.00;
        

        do{
            System.out.println("+----------------------------------------+--------------------------------------------------------+");
            System.out.println("|              OPCIONES                  |                    DETALLE DE VENTA                    |");
            System.out.println("+----------------------------------------+--------------------------------------------------------+");
            System.out.println("|     1. Agregar producto                |                                                        |");
            System.out.println("|     2. Finalizar venta                 | +-----+-----------------------+-------+--------------+ |");
            System.out.println("|     3. Cancelar                        | | CÓD | PRODUCTO              | CANT  | MONTO        | |");
            System.out.println("|                                        | +-----+-----------------------+-------+--------------+ |");
            if(total!=0){
                for(int fila=0;fila<100;fila++){
                    if(!(carrito[fila][0].equals("-100"))){
                        System.out.printf("|                                        | | %-3.3s | %-21.21s | %-5.5s | %-12.12s | |%n", carrito[fila][0], carrito[fila][1], carrito[fila][2], carrito[fila][3]);
                    }
                }
            }else{
                System.out.printf("|                                        | | %-3.3s | %-21.21s | %-5.5s | %-12.12s | |%n", "", "", "", "");
            }
            System.out.println("|                                        | +-----+-----------------------+-------+--------------+ |");
            System.out.println("|                                        |                                                        |");
            System.out.printf("| %-38s | Total: $%-46.2f |%n", " ",total);
            System.out.println("+----------------------------------------+--------------------------------------------------------+");
            opcion = ingresarEntero("*    Elige una opcion (1-3): ");
            switch(opcion){
                case 1:
                    
                    boolean repetido = false;
                    
                    System.out.println("\n+----------------------------------------+");
                    System.out.println("|        AGREGAR PRODUCTO (VENTA)        |");
                    System.out.println("+----------------------------------------+");
                    codigoUnico = ingresarEntero("*    Código: ");
                    System.out.println("+----------------------------------------+");
                    existeId = buscarProducto(codigoUnico);
                    
                    if(existeId!=-100){
                        System.out.println("-    Nombre: "+inventario[existeId][1]);
                        System.out.println("-    Precio: "+inventario[existeId][3]);
                        System.out.println("+----------------------------------------+");
                        cantidad = ingresarEntero("*    Cantidad: ");
                        System.out.println("+----------------------------------------+");
                        
                        for(int fila=0;fila<100;fila++){ //Evalua si ya existe el producto ingresado en el carrito, para modificar solamente la cantidad, monto y el total.
                            if(Integer.parseInt(inventario[existeId][0])==Integer.parseInt(carrito[fila][0])){
                                /*
                                Cantidad Temporal, solo sirve para evaluar si el nuevo monto no es mayor al Stock, antes de asignarla
                                y modificar oficialmente a la variable cantidad.
                                */
                                int tempCantidad = cantidad+Integer.parseInt(carrito[fila][2]); 
                                
                                if(validarStock(existeId, tempCantidad)){
                                    
                                    monto = Integer.parseInt(inventario[existeId][3])*cantidad;
                                    total = total + monto;
                                    cantidad = cantidad+Integer.parseInt(carrito[fila][2]);
                                    monto = Integer.parseInt(inventario[existeId][3])*cantidad;
                                    
                                    carrito[fila][2] = String.valueOf(cantidad);
                                    carrito[fila][3] = String.valueOf(monto);
                                    repetido = true;
                                    
                                    System.out.println("[+]    PRODUCTO AGREGADO AL CARRITO");
                                    System.out.println("       EXITOSAMENTE");
                                }
                                break;
                            }
                        }
                        
                        if(repetido==false && validarStock(existeId, cantidad)){ //Agrega una nueva columna al producto nuevo.
                            
                            monto = Integer.parseInt(inventario[existeId][3])*cantidad;
                            total = total + monto;

                            carrito[contador][0] = inventario[existeId][0];
                            carrito[contador][1] = inventario[existeId][1];
                            carrito[contador][2] = String.valueOf(cantidad);
                            carrito[contador][3] = String.valueOf(monto);
                            
                            System.out.println("[+]    PRODUCTO AGREGADO AL CARRITO");
                            System.out.println("       EXITOSAMENTE");

                            contador++;
                        }       
                        
                        
                        break;
                    }else{
                        System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO");
                        System.out.println("      CON ESE CÓDIGO.");
                        System.out.println("+----------------------------------------+");
                    }
                    
                    break;
                case 2:
                    if(total!=0){
                        
                        LocalDateTime fecha = LocalDateTime.now();
                        DateTimeFormatter fecha2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String fechaFormateada = fecha.format(fecha2);
                        int posicion = generarCodigoUnicoVenta();
                        String cantProd=""; //Variable que va almacenar tanto el codigo del producto como su cantidad tambien.

                        for(int fila=0;fila<100;fila++){
                            if(!carrito[fila][0].equals("-100")){
                                int posicionProducto = buscarProducto(Integer.parseInt(carrito[fila][0]));
                                cantProd=cantProd+carrito[fila][0]+","+carrito[fila][2]+"|";
                                inventario[posicionProducto][4] = String.valueOf(Integer.parseInt(inventario[posicionProducto][4])-Integer.parseInt(carrito[fila][2]));
                            }
                        }
                        ventas[posicion][0] = cantProd;
                        ventas[posicion][1] = fechaFormateada;
                        ventas[posicion][2] = String.valueOf(total);
                        
                        System.out.println("\n+----------------------------------------+");
                        System.out.println("|       LA VENTA HA SIDO REGISTRADA      |");
                        System.out.println("+----------------------------------------+");
                        opcion=3;
                        
                    }else{
                        System.out.println("+----------------------------------------+");
                        System.out.println("[?]   NO SE HA AGREGADO NINGUN PRODUCTO.");
                        System.out.println("+----------------------------------------+");
                    }
                    
                    
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }while(opcion!=3);
    }
    
    public boolean validarStock(int codProdInventario, int cantidadVenta){
        if(Integer.parseInt(inventario[codProdInventario][4])<cantidadVenta){
            System.out.println("[X]    STOCK INSUFICIENTE PARA: "+cantidadVenta+" UNIDADES,");
            System.out.println("       CONTAMOS CON: "+inventario[codProdInventario][4]+" UNIDADES.");
            return false;
        }
        return true;
    }
    
    public int buscarProducto(int codigo){
        for (int fila=0;fila<100;fila++){
            if(inventario[fila][0].equals(String.valueOf(codigo))){ //Si el ID es encontrado retorna la posicion donde se encuentra
                return fila;
            }
        }
        return -100;
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
        codigoUnico = generarCodigoUnicoProducto();
        
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
    
    public int generarCodigoUnicoProducto(){
        return ++ultimoCodigoProducto;
    }
    
    public int generarCodigoUnicoVenta(){
        return ++ultimoCodigoVenta;
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
    
    public void generarEspaciosVaciosInventario(){
        for (int fila=0;fila<100;fila++){
            inventario[fila][0] = "-100";
            for (int col=1;col<5;col++){ //Empieza en 1, porque la seccion de ID ya la modifico arriba.
                inventario[fila][col] = "VACIO";
            }
        }
    }
    
    public String[][] generarEspaciosVaciosCarrito(String [][] carrito){
        for (int fila=0;fila<100;fila++){
            carrito[fila][0] = "-100";
            for (int col=1;col<4;col++){ //Empieza en 1, porque la seccion de ID ya la modifico arriba.
                carrito[fila][col] = "VACIO";
            }
        }
        return carrito;
    }
    
    public void agregarDatosPrueba(){ //FUNCION SOLO PARA TRABAJAR CON PRUEBAS
        
        for (int fila=0;fila<100;fila++){
            inventario[fila][0] = String.valueOf(fila);
            inventario[fila][1] = String.valueOf("Nombre"+(fila));
            inventario[fila][2] = String.valueOf("Categoria"+(fila));
            inventario[fila][3] = String.valueOf(100+(fila*5));
            inventario[fila][4] = String.valueOf(20+(fila*5)); 
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
    
    public void verListadoVentas(){ //FUNCION SOLO PARA TRABAJAR CON PRUEBAS
        int contarPersonajes = 0;
        System.out.println("+-----+-------------------------------------+----------------------------------------+-----------+");
        System.out.println("| CODIGO Y CANTIDAD VENDIDA                 | FECHA Y HORA                           | TOTAL     |");
        System.out.println("+-----+-------------------------------------+----------------------------------------+-----------+");
        for (int fila=0;fila<100;fila++){
//            if(!(inventario[fila][0].equals("100"))){ //No se muestran las IDS 100, o sea las que no contienen datos.
                System.out.printf("| %-24.24s | %-20.20s | %-10.10s | %n", 
                    ventas[fila][0],
                    ventas[fila][1],
                    ventas[fila][2]
                );
                contarPersonajes++;
//            }
        }
        if(contarPersonajes == 0){
            System.out.println("+-----+----------------------+----------------------+--------------------------------------------------+-------+");
            System.out.println("NO HAY VENTAS AGREGADOS.");
        }else{
            System.out.println("+-----+----------------------+----------------------+--------------------------------------------------+-------+");
            System.out.println("Ventas agregadas: ["+contarPersonajes+"/100]");
        }
    }
}
