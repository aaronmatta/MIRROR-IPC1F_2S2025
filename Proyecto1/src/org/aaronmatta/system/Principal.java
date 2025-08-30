package org.aaronmatta.system;

import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;

public class Principal {

    //___ DECLARACION DE VARIABLES, ARRAYS, ETC. ___
    
    Scanner scanner = new Scanner(System.in);
    
    String nombreUsuario; 
    String carnetUsuario;
    String usuario;
    
    String nombre, categoria;
    double precio;
    int opcion, cantidadStock, codigoUnico, cantidad;
    
    int ultimoCodigoProducto=-1, ultimoCodigoVenta = -1, ultimoCodigoBitacora=-1;
        
    String[][] inventario = new String[100][5];
    String[][] ventas = new String[100][3];
    String[][] bitacora = new String[200][5];
    
    //ACCIONES DE LA BITACORA
    String TIPOACCION_AGREGAR = "AGREGAR";
    String TIPOACCION_BUSCAR = "BUSCAR";
    String TIPOACCION_ELIMINAR = "ELIMINAR";
    String TIPOACCION_REGISTRAR = "REGISTRAR";
    String TIPOACCION_GENERAR = "GENERAR";
    String TIPOACCION_VER = "VER";
    
    String ACCION_CORRECTA = "CORRECTA";
    String ACCION_ERRONEA = "ERRONEA";
    
    //______________________________________________
    
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.generarEspaciosVaciosInventario();
        programa.agregarDatosPrueba();
        programa.menu();
        
    }

    public void menu(){
        System.out.println("\n+----------------------------------------+");
        System.out.println("|           INGRESA TU NOMBRE            |");
        System.out.println("+----------------------------------------+");
        nombreUsuario = ingresarTexto("*    Nombre: ");
        System.out.println("\n+----------------------------------------+");
        System.out.println("|           INGRESA TU CARNET            |");
        System.out.println("+----------------------------------------+");
        carnetUsuario = String.valueOf(ingresarEntero("*    Carnet: "));
        System.out.println("+----------------------------------------+");
        System.out.println("[✓]    DATOS DEL USUARIO GUARDADOS.");
        usuario = nombreUsuario+","+carnetUsuario;
        System.out.println("+----------------------------------------+");
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
                    do{
                        System.out.println("\n+----------------------------------------+");
                        System.out.println("|            GENERAR REPORTES            |");
                        System.out.println("+----------------------------------------+");
                        System.out.println("|    1. Reporte de Stock.                |");
                        System.out.println("|    2. Reporte de Ventas.               |");
                        System.out.println("|    3. Regresar                         |");
                        System.out.println("+----------------------------------------+");
                        opcion = ingresarEntero("*    Elige una opcion (1-3): ");
                        switch(opcion){
                            case 1:
                                generarPdfStock();
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("\n[?]    INGRESE UNA DE LAS OPCIONES VÁLIDAS.");
                                break;
                        }
                        
                    }while(opcion!=3);
                    break;
                case 6:
                    mostrarDatosEstudiante();
                    break;
                case 7:
                    verListadoBitacora();
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
    
    public void generarPdfStock(){
        
        LocalDateTime fechaSinFormato = LocalDateTime.now();
        String fechaArchivo = fechaSinFormato.format(DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_ss"));
        String fechaTexto =fechaSinFormato.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        
        String rutaArchivo = "C:\\Users\\TheSn\\Downloads\\"+fechaArchivo+"_Stock.pdf";
        Document documento = new Document();

        try {
        
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));

            documento.open();
            
            // Estilos
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
            Font textoFont1 = FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.GRAY);
            Font textoFont2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.RED);
            Font encabezadoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            Font celdaFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // Titulo/Informacion
            Paragraph titulo = new Paragraph("REPORTE DE INVENTARIO - STOCK", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            
            Paragraph informacion = new Paragraph(
                "Generado el: "+fechaTexto+"    |    Usuario: "+nombreUsuario+"    |    Carnet: "+carnetUsuario, textoFont1
            );
            informacion.setAlignment(Element.ALIGN_CENTER);
            informacion.setSpacingAfter(15);
            documento.add(informacion);
            
            // Crear la tabla
            PdfPTable tabla = new PdfPTable(5);
            float[] anchosColumnas = {1f, 3f, 2f, 1.5f, 1.5f};
            tabla.setWidthPercentage(100);
            tabla.setWidths(anchosColumnas);

            // Encabezados
            PdfPCell h1 = new PdfPCell(new Paragraph("ID", encabezadoFont));
            h1.setBackgroundColor(new BaseColor(36, 48, 166));
            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
            h1.setBorderWidth(1.2f);
            h1.setPadding(8);
            tabla.addCell(h1);

            PdfPCell h2 = new PdfPCell(new Paragraph("Nombre", encabezadoFont));
            h2.setBackgroundColor(new BaseColor(36, 48, 166));
            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
            h2.setBorderWidth(1.2f);
            h2.setPadding(8);
            tabla.addCell(h2);

            PdfPCell h3 = new PdfPCell(new Paragraph("Categoria", encabezadoFont));
            h3.setBackgroundColor(new BaseColor(36, 48, 166));
            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
            h3.setBorderWidth(1.2f);
            h3.setPadding(8);
            tabla.addCell(h3);

            PdfPCell h4 = new PdfPCell(new Paragraph("Precio", encabezadoFont));
            h4.setBackgroundColor(new BaseColor(36, 48, 166));
            h4.setHorizontalAlignment(Element.ALIGN_CENTER);
            h4.setBorderWidth(1.2f);
            h4.setPadding(8);
            tabla.addCell(h4);

            PdfPCell h5 = new PdfPCell(new Paragraph("Stock", encabezadoFont));
            h5.setBackgroundColor(new BaseColor(36, 48, 166));
            h5.setHorizontalAlignment(Element.ALIGN_CENTER);
            h5.setBorderWidth(1.2f);
            h5.setPadding(8);
            tabla.addCell(h5);
            
            int contarFilas = 0;

            // Agregar cada fila y columna
            for(int fila=0;fila<100;fila++){
                if(!inventario[fila][0].equals("-100")){
                    contarFilas++;
                    
                    PdfPCell celdaCodigo = new PdfPCell(new Phrase(inventario[fila][0], celdaFont));
                    celdaCodigo.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celdaCodigo.setPadding(5);
                    tabla.addCell(celdaCodigo);
                    
                    PdfPCell celdaNombre = new PdfPCell(new Phrase(inventario[fila][1], celdaFont));
                    celdaNombre.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celdaNombre.setPadding(5);
                    tabla.addCell(celdaNombre);
                    
                    PdfPCell celdaCategoria = new PdfPCell(new Phrase(inventario[fila][2], celdaFont));
                    celdaCategoria.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdaCategoria.setPadding(5);
                    tabla.addCell(celdaCategoria);
                    
                    PdfPCell celdaPrecio = new PdfPCell(new Phrase(inventario[fila][3], celdaFont));
                    celdaPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    celdaPrecio.setPadding(5);
                    tabla.addCell(celdaPrecio);
                    
                    PdfPCell celdaStock = new PdfPCell(new Phrase(inventario[fila][4], celdaFont));
                    celdaStock.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    celdaStock.setPadding(5);
                    tabla.addCell(celdaStock);
                        
                }
            }
            
            // Añadir la tabla al documento
            documento.add(tabla);
            
            if(contarFilas==0){
                Paragraph sinProductos = new Paragraph("NO HAY PRODUCTOS AGREGADOS EN EL INVENTARIO.", textoFont2);
                sinProductos.setSpacingAfter(15);
                sinProductos.setAlignment(Element.ALIGN_CENTER);
                documento.add(sinProductos);
            }

            System.out.println("Tabla generada con éxito.");
            System.out.println("PDF creado en la siguiente ruta: " + rutaArchivo);
            registrarBitacora(TIPOACCION_GENERAR,ACCION_CORRECTA,usuario,"PDF STOCK GENERADO");

        } catch (Exception e) {
            e.printStackTrace();
            registrarBitacora(TIPOACCION_GENERAR,ACCION_ERRONEA,usuario,"ERROR AL GENERAR PDF STOCK");
        } finally {
            // Cerrar documento
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }
    
    public void registrarBitacora(String tipoAccion, String accion, String usuario, String mensaje){
        String fecha = generarFechaHoraActual();
        int posicion = generarCodigoUnicoBitacora();
        if(!(posicion>200)){
            bitacora[posicion][0]= fecha;
            bitacora[posicion][1]= tipoAccion;
            bitacora[posicion][2]= accion;
            bitacora[posicion][3]= usuario;
            bitacora[posicion][4]= mensaje;
        }
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
                                int tempCantidad = cantidad + Integer.parseInt(carrito[fila][2]); 
                                
                                if(validarStock(existeId, tempCantidad)){
                                    
                                    monto = Double.parseDouble(inventario[existeId][3])*cantidad;
                                    total = total + monto;
                                    cantidad = cantidad+Integer.parseInt(carrito[fila][2]);
                                    monto = Double.parseDouble(inventario[existeId][3])*cantidad;
                                    
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
                            
                            monto = Double.parseDouble(inventario[existeId][3])*cantidad;
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
                    }
                    
                    break;
                case 2:
                    if(total!=0){
                        
                        String fecha = generarFechaHoraActual();
                        int posicion = generarCodigoUnicoVenta();
                        
                        if(posicion>200){
                            System.out.println("+----------------------------------------+");
                            System.out.println("[?]   SE HA ALCANZADO EL CUPO MAXIMO DE ");
                            System.out.println("      VENTAS PARA ALMACENAR.             ");
                            System.out.println("+----------------------------------------+");
                            registrarBitacora(TIPOACCION_REGISTRAR,ACCION_ERRONEA,usuario,"ESPACIO PARA ALMACENAR VENTAS ESTA LLENO");
                            break;
                        }
                        
                        String cantProd=""; //Variable que va almacenar tanto el codigo del producto como su cantidad tambien.
                        
                        for(int fila=0;fila<100;fila++){
                            if(!carrito[fila][0].equals("-100")){
                                int posicionProducto = buscarProducto(Integer.parseInt(carrito[fila][0]));
                                cantProd=cantProd+carrito[fila][0]+","+carrito[fila][2]+"|";
                                inventario[posicionProducto][4] = String.valueOf(Integer.parseInt(inventario[posicionProducto][4])-Integer.parseInt(carrito[fila][2])); //Restar del inventario
                            }
                        }
                        ventas[posicion][0] = cantProd;
                        ventas[posicion][1] = fecha;
                        ventas[posicion][2] = String.valueOf(total);
                        
                        System.out.println("\n+----------------------------------------+");
                        System.out.println("|       LA VENTA HA SIDO REGISTRADA      |");
                        System.out.println("+----------------------------------------+");
                        registrarBitacora(TIPOACCION_REGISTRAR,ACCION_CORRECTA,usuario,"PRODUCTOS: "+cantProd+"    "+"TOTAL: "+total );
                        opcion=3;
                        
                    }else{
                        System.out.println("+----------------------------------------+");
                        System.out.println("[?]   NO SE HA AGREGADO NINGUN PRODUCTO.");
                        System.out.println("+----------------------------------------+");
                        registrarBitacora(TIPOACCION_REGISTRAR,ACCION_ERRONEA,usuario,"SE INTENTO REGISTRAR VENTA SIN PRODUCTOS AGREGADOS");
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
                    registrarBitacora(TIPOACCION_ELIMINAR,ACCION_CORRECTA,usuario,"CODIGO: "+codigo);
                    break;
                }
                
                System.out.println("+----------------------------------------+");
                System.out.println("[X]    ACCION CANCELADA.");
                registrarBitacora(TIPOACCION_ELIMINAR,ACCION_ERRONEA,usuario,"EL USUARIO CANCELO LA ELIMINACION DEL PRODUCTO CON CODIGO: "+codigo);
                return;
                
            }
        }
        if(!encontrado){
            System.out.println("+----------------------------------------+");
            System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO");
            System.out.println("      CON ESE CÓDIGO.");
            registrarBitacora(TIPOACCION_ELIMINAR,ACCION_ERRONEA,usuario,"NINGUN PRODUCTO ENCONTRADO CON CODIGO: "+codigo);
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
                        registrarBitacora(TIPOACCION_BUSCAR,ACCION_CORRECTA,usuario,"CODIGO: "+textoIngresado);
                        contador++;
                        break;
                    }
                }
                if(contador==0){
                    System.out.println("[?]   NO SE HA ENCONTRADO NINGUN PRODUCTO.");
                    registrarBitacora(TIPOACCION_BUSCAR,ACCION_ERRONEA,usuario,"NINGUN PRODUCTO CON CODIGO: "+textoIngresado);
                    break;
                }
                break;
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
                    registrarBitacora(TIPOACCION_BUSCAR,ACCION_ERRONEA,usuario,"NINGUN PRODUCTO CON NOMBRE: "+textoIngresado);
                    break;
                }
                registrarBitacora(TIPOACCION_BUSCAR,ACCION_CORRECTA,usuario,"NOMBRE: "+textoIngresado);
                break;
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
                    registrarBitacora(TIPOACCION_BUSCAR,ACCION_ERRONEA,usuario,"NINGUN PRODUCTO CON CATEGORIA: "+textoIngresado);
                    break;
                }
                registrarBitacora(TIPOACCION_BUSCAR,ACCION_CORRECTA,usuario,"CATEGORIA: "+textoIngresado);
                break;
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
            registrarBitacora(TIPOACCION_AGREGAR,ACCION_ERRONEA,usuario,"INVENTARIO LLENO");
            return;
        }
        
        double confirmarPrecio = validarPositivo(1,precio);
        int confirmarStock = (int) validarPositivo(2,cantidadStock);
        
        if( confirmarPrecio != -100 && confirmarStock != -100){
            
            codigoUnico = generarCodigoUnicoProducto();
            
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
                    registrarBitacora(TIPOACCION_AGREGAR,ACCION_CORRECTA,usuario,"CÓDIGO: "+codigoUnico);
                    return;
                }
            }
        }
        registrarBitacora(TIPOACCION_AGREGAR,ACCION_ERRONEA,usuario,"NÚMERO INGRESADO DE PRECIO/STOCK INVÁLIDO");
        
    }
    
    public int generarCodigoUnicoBitacora(){
        return ++ultimoCodigoBitacora;
    }
    
    public int generarCodigoUnicoProducto(){
        return ++ultimoCodigoProducto;
    }
    
    public int generarCodigoUnicoVenta(){
        return ++ultimoCodigoVenta;
    }
    
    public String generarFechaHoraActual(){
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter fecha2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = fecha.format(fecha2);
        return fechaFormateada;
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
        registrarBitacora(TIPOACCION_VER,ACCION_CORRECTA,usuario,"SE MOSTRÓ LA INFORMACIÓN DEL ESTUDIANTE");
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
        
        usuario = "Gerson,202403711";
        
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
            if(!(inventario[fila][0].equals("-100"))){ //No se muestran las IDS 100, o sea las que no contienen datos.
                System.out.printf("| %-4s | %-20.20s | %-20.20s | %-48.48s | %-5s |%n", 
                    inventario[fila][0],
                    inventario[fila][1],
                    inventario[fila][2],
                    inventario[fila][3],
                    inventario[fila][4]
                
                );
                contarPersonajes++;
            }
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
            if(!(inventario[fila][0]!=null)){ //No se muestran las IDS 100, o sea las que no contienen datos.
                System.out.printf("| %-24.24s | %-20.20s | %-10.10s | %n", 
                    ventas[fila][0],
                    ventas[fila][1],
                    ventas[fila][2]
                );
                contarPersonajes++;
            }
        }
        if(contarPersonajes == 0){
            System.out.println("+-----+----------------------+----------------------+--------------------------------------------------+-------+");
            System.out.println("NO HAY VENTAS AGREGADOS.");
        }else{
            System.out.println("+-----+----------------------+----------------------+--------------------------------------------------+-------+");
            System.out.println("Ventas agregadas: ["+contarPersonajes+"/100]");
        }
    }
    
    public void verListadoBitacora(){
        int contarBitacoras = 0;
        System.out.println("\n+----------------------+-----------------+------------+----------------------+---------------------------------------------------------------------------------------+");
        System.out.println("| FECHA Y HORA         | TIPO DE ACCION  | ACCION     | USUARIO              | MENSAJE                                                                               |");
        System.out.println("+----------------------+-----------------+------------+----------------------+---------------------------------------------------------------------------------------+");
        for (int fila=0;fila<200;fila++){
            if(bitacora[fila][0]!=null){ //No se muestran las IDS 100, o sea las que no contienen datos.
                System.out.printf("| %-20.20s | %-15.15s | %-10.10s | %-20.20S | %-85.85S | %n", 
                    bitacora[fila][0],
                    bitacora[fila][1],
                    bitacora[fila][2],
                    bitacora[fila][3],
                    bitacora[fila][4]
                );
                contarBitacoras++;
            }
        }
        if(contarBitacoras == 0){
            System.out.println("+----------------------+-----------------+------------+----------------------+---------------------------------------------------------------------------------------+");
            System.out.println("NO HAY REGISTROS.");
        }else{
            System.out.println("+----------------------+-----------------+------------+----------------------+---------------------------------------------------------------------------------------+");
            System.out.println("Bitacoras agregadas: ["+contarBitacoras+"/200]");
        }
    }
}
