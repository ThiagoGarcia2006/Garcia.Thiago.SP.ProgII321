package Servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import modelo.Personaje;

public class Inventario<T extends Comparable<T> & Serializable> {
    
    private List<T> items = new ArrayList<>();      

    
    public void agregar(T item) {
        if(items == null){
            throw new IllegalArgumentException ("No puedo almacenar algo nulo");
        }
        items.add(item);
    }

    
    public void eliminar(int indice) {
        validarIndice(indice);
        items.remove(indice);
        
        for (T item: items){
            System.out.println(item);
        }
    }

    public void listar() {
        if (items == null || items.isEmpty()) {
            System.out.println("No hay elementos en la colección.");
        } else {
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    public void ordenar() {
        if (items == null || items.isEmpty()) {
            System.out.println("No hay elementos para ordenar.");
        } else {
            Collections.sort(items);
            System.out.println("Elementos ordenados por el criterio natural.");
        }
    }

    public void ordenar(Comparator<T> comparator) {
    if (items == null || items.isEmpty()) {
        System.out.println("No hay elementos para ordenar.");
    } else if (comparator == null) {
        throw new IllegalArgumentException("El Comparator no puede ser nulo.");
    } else {
        items.sort(comparator);
        System.out.println("Elementos ordenados según el criterio personalizado.");
    }
}

    
    private void validarIndice(int indice){
        if (!(indice < 0 || indice >= items.size())) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
    }

    
    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> toReturn = new ArrayList<>();
        for(T item : items){
            if(criterio.test(item)){
                toReturn.add(item);
            }
        }
        
        return toReturn;
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public void setItems(List<T> items) {
        this.items = items;
    }
    
    public void paraCadaElemento(Consumer<? super T> accion) {
        for (T item : items){
            accion.accept(item);
        }
    }
    
    public List<T> transformar(Function<? super T, ? extends T> transformacion) {
        List<T> toReturn = new ArrayList<>();
        for(T item : items){
            toReturn.add(transformacion.apply(item));
        }
        return toReturn;
    }
    
    public static List<Personaje> cargarPersonajes(String path) {
        List<Personaje> toReturn = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(path))) {
            toReturn = (List<Personaje>) entrada.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Error al cargar personajes: " + ex.getMessage());
        }
        return toReturn;
    }

    
    public static void guardarPersonajesCSV(List<? extends Personaje> lista, String path){
        File archivo = new File(path);
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
             bw.write("id, nombre, clase, nivel \n");
            for(Personaje p : lista){
                bw.write(p.toCSV() + "\n");
            }
                       
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public static List<Personaje> cargarPersonajesCSV(String path){
        List<Personaje> toReturn = new ArrayList<>();
        File archivo = new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); 
            while ((linea = br.readLine()) != null) {
                if (linea.endsWith("\n")) {
                    linea = linea.substring(0, linea.length() - 1);
                }
                toReturn.add(Personaje.fromCSV(linea));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return toReturn;
    }
    
    public static void serializarPersonajes(List<? extends Personaje> lista, String path){
        try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(path))){           
            salida.writeObject(lista);
                      
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}

