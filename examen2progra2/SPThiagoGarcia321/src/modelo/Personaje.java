package modelo;

import java.io.Serializable;


public class Personaje implements Comparable<Personaje>, Serializable{
    private int id;
    private String nombre;
    private Clase clase;
    private int nivel;

    public Personaje(int id, String nombre, Clase clase, int nivel) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
    }

    public Clase getClase() {
        return clase;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
    
    @Override
    public int compareTo(Personaje otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    @Override
    public String toString() {
        return "Personaje{" + "id=" + id + ", nombre=" + nombre + ", clase=" + clase + ", nivel=" + nivel + '}';
    }
    
    
    public String toCSV(){
        return id + "," + nombre + "," + clase.toString() + "," + nivel;
    }
    

    
    public static Personaje fromCSV(String personajeCSV) {
        Personaje toReturn = null;
        String[] values = personajeCSV.split(",");
        if(values.length == 4){
            int id = Integer.parseInt(values[0]);
            String nombre = values[1];
            Clase clase = Clase.valueOf(values[2]);
            int nivel = Integer.parseInt(values[3]);
                    
            toReturn = new Personaje(id, nombre, clase, nivel);
        }
        return toReturn; 
    }
}
