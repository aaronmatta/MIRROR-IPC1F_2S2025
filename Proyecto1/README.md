# Sistema de Gestión de Inventario y Ventas

![Java](https://img.shields.io/badge/Java-24%2B-blue?logo=java)
![NetBeans](https://img.shields.io/badge/NetBeans-Compatible-orange?logo=apache-netbeans-ide)

Un sistema de gestión de inventario  y ventas para una tienda de ropa desarrollado en Java, que permite administrar productos, registrar transacciones y generar reportes en formato PDF, permitiendo la gestión de productos, ventas y reportes, mediante estructuras de datos, ciclos, validaciones.

## Características principales

- **Gestión de productos**:
  - Agregar
  - Buscar
  - Eliminar
- **Gestión de ventas**:
  - Registro de transacciones con control de stock
- **Generación de reportes**:
  - Creación de reportes en PDF para inventario y ventas
- **Bitácora de operaciones**:
  - Registro detallado de todas las acciones realizadas
- **Sistema de categorías**:
  - 10 categorías predefinidas para organizar productos

## Instalación y ejecución

### Requisitos previos

- Java JDK 24
- Biblioteca iText para generación de PDFs
- NetBeans IDE (opcional)

### Ejecutar desde NetBeans

1. Clonar el repositorio
   
   ```bash
   git clone https://github.com/aaronmatta/IPC1F_2S2025.git
   ```

2. Abrir proyecto en NetBeans

3. Ejecutar `Principal.java`

### Ejecutar desde terminal

```bash
# Compilar
javac -cp .;itextpdf-5.5.10.jar org/aaronmatta/system/Principal.java

# Ejecutar
java -cp .;itextpdf-5.5.10.jar org.aaronmatta.system.Principal
```

## Estructura de datos

### Inventario (capacidad: 100 productos)
| Campo | Tipo | Descripción |
| --- | --- | --- |
| Código | String | Identificador único, autoincrementable |
| Nombre | String | Nombre del producto |
| Categoría | String | Categoría del producto |
| Precio | String | Precio unitario |
| Stock | String | Cantidad disponible |

### Ventas (capacidad: 100 transacciones)

| Campo | Tipo | Descripción |
| --- | --- | --- |
| Detalles | String | Productos vendidos (código, nombre, cantidad) |
| Fecha/Hora | String | Timestamp de la transacción |
| Total | String | Monto total de la venta |

### Bitácora (capacidad: 200 registros)

| Campo | Tipo | Descripción |
| --- | --- | --- |
| Fecha/Hora | String | Timestamp de la operación |
| Tipo Acción | String | Tipo de acción realizada |
| Resultado | String | Resultado (correcta/errónea) |
| Usuario | String | Usuario que realizó la acción |
| Mensaje | String | Detalles adicionales |

## Vistas

### Menú principal

```plaintext
+----------------------------------------+
|             MENÚ PRINCIPAL             |
+----------------------------------------+
|    1. Agregar Producto.                |
|    2. Buscar Producto.                 |
|    3. Eliminar Producto.               |
|    4. Registrar Venta.                 |
|    5. Generar Reportes.                |
|    6. Ver Datos del Estudiante.        |
|    7. Bitácora.                        |
|    8. Salir.                           |
+----------------------------------------+
```

### Agregar producto

```plaintext
+----------------------------------------+
|            AGREGAR PRODUCTO            |
+----------------------------------------+
*    Nombre: Camisa Azul
      1. Camisetas y blusas
      2. Pantalones y jeans
      3. Faldas y vestidos
      4. Chamarras y abrigos
      5. Suéteres y sudaderas
      6. Ropa interior
      7. Calzado
      8. Accesorios
      9. Ropa deportiva
      10. Ropa de dormir
*    Categoría: 1
*    Precio: 12.50
*    Cantidad en Stock: 50
+----------------------------------------+
[+]    PRODUCTO AGREGADO EXITOSAMENTE.
       CON CÓDIGO: 0
```

### Buscar producto

```plaintext
+----------------------------------------+
|            BUSCAR  PRODUCTO            |
+----------------------------------------+
|    1. Buscar por código.               |
|    2. Buscar por nombre.               |
|    3. Buscar por categoría.            |
|    4. Regresar         .               |
+----------------------------------------+
*    Elige una opcion (1-4): 1

+----------------------------------------+
|        BUSCAR PRODUCTO (CÓDIGO)        |
+----------------------------------------+
*    Código: 0
+----------------------------------------+
-    Código: 0
-    Nombre: Camisa Azul
-    Categoría: Pantalones y jeans
-    Precio: 12.5
-    Stock: 50
+----------------------------------------+
```

### Eliminar producto

```plaintext
+----------------------------------------+
|           ELIMINAR  PRODUCTO           |
+----------------------------------------+
*    Código: 0
+----------------------------------------+
[✓]    ESTAS SEGURO DE ELIMINAR EL
       PRODUCTO Camisa Azul, 
       CON CÓDIGO 0 ?
[X]    VUELVE A ESCRIBIR SU CODIGO
       PARA CONFIRMAR: 0
+----------------------------------------+
[-]    PRODUCTO ELIMINADO EXITOSAMENTE.
```

### Registrar venta

```plaintext
+----------------------------------------+--------------------------------------------------------+
|              OPCIONES                  |                    DETALLE DE VENTA                    |
+----------------------------------------+--------------------------------------------------------+
|     1. Agregar producto                |                                                        |
|     2. Finalizar venta                 | +-----+-----------------------+-------+--------------+ |
|     3. Cancelar                        | | CÓD | PRODUCTO              | CANT  | MONTO        | |
|                                        | +-----+-----------------------+-------+--------------+ |
|                                        | | 1   | Camisa Azul           | 3     | 37.5         | |
|                                        | | 2   | Pulsera               | 2     | 9.0          | |
|                                        | +-----+-----------------------+-------+--------------+ |
|                                        |                                                        |
|                                        | Total: $46.50                                          |
+----------------------------------------+--------------------------------------------------------+
*    Elige una opcion (1-3): 
```

## Reportes PDF
El sistema genera reportes en PDF con la siguiente información:
- Encabezado con fecha de generación y información del usuario
- Tablas detalladas con la información solicitada
- Los archivos se guardan con el formato: `dd_MM_YYYY_HH_mm_ss_[Tipo].pdf`

## Validaciones implementadas

- Campos de texto solo aceptan caracteres alfabéticos
- Campos numéricos validan valores positivos
- Verificación de stock suficiente antes de registrar ventas
- Control de duplicados en el inventario
- Validación de existencia de productos antes de operaciones
  
## Categorías disponibles
1. Camisetas y blusas
2. Pantalones y jeans
3. Faldas y vestidos
4. Chamarras y abrigos
5. Suéteres y sudaderas
6. Ropa interior
7. Calzado
8. Accesorios
9. Ropa deportiva
10. Ropa de dormir
  
## Limitaciones conocidas
- Los datos se almacenan en memoria durante la ejecución (no persisten al cerrar)
- Capacidad limitada a 100 productos y 100 ventas
- La ruta de guardado de PDFs es fija.
