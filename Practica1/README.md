# Sistema de Gestión de Personajes ⚔️

![Java](https://img.shields.io/badge/Java-24%2B-blue?logo=java)
![NetBeans](https://img.shields.io/badge/NetBeans-Compatible-orange?logo=apache-netbeans-ide)

Un sistema de gestión de personajes que permite registrar, modificar y visualizar personajes con sus atributos, así como registrar peleas entre ellos y mantener un historial de combates.

## Características principales 🚀

- **Gestión completa de personajes**:
  
  - Creación con nombre, arma, habilidades y nivel.
  - Modificación de atributos específicos.
  - Eliminación con limpieza de peleas relacionadas.
  - Visualización detallada e informe tabular.

- **Sistema de combates**:
  
  - Registro de peleas con timestamp.
  - Historial completo con formato tabular.
  - Validación de personajes existentes.

- **Interfaz intuitiva**:
  
  - Menú interactivo con navegación clara.
  - Formatos tabulares para mejor visualización.
  - Mensajes de validación y confirmación.

## Instalación y ejecución 💻

### Requisitos previos

- Java JDK 24
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
javac -d . src/org/aaronmatta/system/Principal.java

# Ejecutar
java org.aaronmatta.system.Principal
```

## Estructura de datos 🗃️

### Personajes (máx. 25)

| Campo       | Tipo   | Descripción                    |
| ----------- | ------ | ------------------------------ |
| ID          | String | Identificador único (1-25)     |
| Nombre      | String | Nombre único del personaje     |
| Arma        | String | Arma principal                 |
| Habilidades | String | Habilidades separadas por coma |
| Nivel       | String | Nivel entre 1-100              |

### Peleas (máx. 50)

| Campo          | Tipo   | Descripción                              |
| -------------- | ------ | ---------------------------------------- |
| Código         | String | ID único de pelea (1-50)                 |
| ID Personaje 1 | String | Primer combatiente                       |
| ID Personaje 2 | String | Segundo combatiente                      |
| Fecha/Hora     | String | Timestamp en formato dd-MM-yyyy HH:mm:ss |

## Validaciones implementadas ✅

| Función             | Validaciones                                      |
| ------------------- | ------------------------------------------------- |
| Agregar personaje   | Nombre único, nivel 1-100, máx. 5 habilidades     |
| Modificar personaje | Existencia de ID, formato correcto de habilidades |
| Eliminar personaje  | Existencia de ID, limpieza de peleas relacionadas |
| Registrar pelea     | Existencia de ambos IDs, espacio disponible       |

## Vistas 🖼️

**Menú principal**  

```plaintext
--- MENÚ PRINCIPAL ---
1. Agregar personaje.
2. Modificar personaje.
3. Eliminar personaje.
4. Ver datos de un personaje.
5. Ver listado de personajes.
6. Realizar pelea entre personajes.
7. Ver historial de peleas.
8. Ver datos de estudiante.
9. Salir
Elige una opcion (1-9): 
```

**Listado de personajes**  

```plaintext
+-----+----------------------+----------------------+--------------------------------------------------+-------+
| ID  | NOMBRE               | ARMA                 | HABILIDADES                                      | NIVEL |
+-----+----------------------+----------------------+--------------------------------------------------+-------+
| 1   | Guerrero             | Espada larga         | Golpe crítico,Defensa fuerte                     | 85    |
| 3   | Mago                 | Bastón arcano        | Bola de fuego,Escarcha,Teletransportación        | 92    |
+-----+----------------------+----------------------+--------------------------------------------------+-------+
Personajes agregados: [2/25]
```

**Historial de peleas**  

```plaintext
+-----+-------------------+-------------------+------------------------------------------+
| COD | ID PERSONAJE 1    | ID PERSONAJE 2    | FECHA Y HORA                             |
+-----+-------------------+-------------------+------------------------------------------+
| 1   | 1                 | 3                 | 15-08-2025 14:30:45                      |
+-----+-------------------+-------------------+------------------------------------------+
Peleas registradas: [1/50]
```

## Problemas conocidos ⚠️

1. **Validación de entradas**:
   - Cantidad de personajes y peleas limitadas.
   - Toda la información se pierde al finalizar la ejecución

## Hoja de ruta y mejoras futuras 🚧

- [ ] Mejorar interfaz gráfica.
- [ ] Agregar estadísticas de combate (ganadores, porcentajes)
- [ ] Implementar nuevas validaciones para evitar errores.
