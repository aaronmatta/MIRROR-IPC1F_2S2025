package org.aaronmatta.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        try {
            ejecutarEjercicioSerializacion();
            ejecutarEjercicioHiloThread();
            ejecutarEjercicioRunnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ejercicio 1: Serialización de Lista de Objetos
    private static void ejecutarEjercicioSerializacion() throws IOException, ClassNotFoundException {
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("Libro 1", "Autor 1", 1967));
        libros.add(new Libro("Libro 2", "Autor 2", 1605));
        libros.add(new Libro("Libro 3", "Autor 3", 2001));

        // Guardar
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libros.ser"));
        oos.writeObject(libros);
        oos.close();

        System.out.println("Lista de libros guardada en libros.ser");

        // Leer
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("libros.ser"));
        List<Libro> librosDeserializados = (List<Libro>) ois.readObject();
        ois.close();

        System.out.println("Libros leídos:");
        for (Libro libro : librosDeserializados) {
            System.out.println(libro);
        }
    }

    // Ejercicio 2: Hilo con Thread - Mensaje Repetido
    private static void ejecutarEjercicioHiloThread() throws InterruptedException {
        MensajeThread mensajeThread = new MensajeThread();
        mensajeThread.start();
        mensajeThread.join();
    }

    // Ejercicio 3: Hilo con Runnable - Suma Progresiva
    private static void ejecutarEjercicioRunnable() throws InterruptedException {
        Thread sumadorThread = new Thread(new SumadorRunnable());
        sumadorThread.start();
        sumadorThread.join();
    }
}

// Ejercicio 1: Serialización de Lista de Objetos
class Libro implements Serializable {
    public String titulo;
    public String autor;
    public int anio;

    public Libro(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + " Autor: " + autor + " Año: " + anio;
    }
}

// Ejercicio 2: Hilo con Thread - Mensaje Repetido
class MensajeThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Mensaje desde un hilo");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Hilo de mensaje interrumpido");
                return;
            }
        }
    }
}

// Ejercicio 3: Hilo con Runnable - Suma Progresiva
class SumadorRunnable implements Runnable {
    @Override
    public void run() {
        int suma = 0;
        for (int i = 1; i <= 10; i++) {
            suma += i;
            System.out.println("Sumando: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Hilo del sumador interrumpido");
                return;
            }
        }
        System.out.println("Total: " + suma);
    }
}