package spthiagogarcia321;

import Servicio.Inventario;
import modelo.Clase;
import modelo.Personaje;

public class test {

    
    public static void main(String[] args) {  
        
            Inventario<Personaje> inventarioPersonajes = new Inventario<>();

          
            inventarioPersonajes.agregar(new Personaje(1, "Aragorn", Clase.GUERRERO, 20));
            inventarioPersonajes.agregar(new Personaje(2, "Gandalf", Clase.MAGO, 50));
            inventarioPersonajes.agregar(new Personaje(3, "Legolas", Clase.ARQUERO, 25));
            inventarioPersonajes.agregar(new Personaje(4, "Frodo", Clase.GUERRERO, 10));
            inventarioPersonajes.agregar(new Personaje(5, "Saruman", Clase.MAGO, 40));
            inventarioPersonajes.agregar(new Personaje(6, "Robin Hood", Clase.ARQUERO, 30));

          
            System.out.println("Inventario de personajes:");
            inventarioPersonajes.paraCadaElemento(System.out::println);

            separador();
            
            System.out.println("Personajes ordenados por nombre (orden natural): ");
            inventarioPersonajes.ordenar();
            inventarioPersonajes.paraCadaElemento(System.out::println);

            separador();
            
            System.out.println("Personajes ordenados por nivel: ");
            inventarioPersonajes.ordenar((p1, p2) -> Integer.compare(p1.getNivel(), p2.getNivel()));
            inventarioPersonajes.paraCadaElemento(System.out::println);

            separador();
            
            System.out.println("Personajes de la clase MAGO:");
            inventarioPersonajes.filtrar(p -> p.getClase() == Clase.MAGO)
                    .forEach(System.out::println);

            separador();
            
            System.out.println("Aumentando nivel de todos los personajes en +5: ");
            inventarioPersonajes.transformar(p -> {
                p.setNivel(p.getNivel() + 5);
                return p;
            }).forEach(System.out::println);

            
            String archivoBinario = "src/data/personajes.dat";
            Inventario.serializarPersonajes(inventarioPersonajes.getItems(), archivoBinario);

            
            Inventario<Personaje> inventarioCargadoBinario = new Inventario<>();
            inventarioCargadoBinario.cargarPersonajes(archivoBinario);
            System.out.println("Personajes cargados desde archivo binario: ");
            inventarioCargadoBinario.paraCadaElemento(System.out::println);

            
            String archivoCSV = "src/data/personajes.csv";
            Inventario.guardarPersonajesCSV(inventarioPersonajes.getItems(), archivoCSV);
            
            separador();
           
            Inventario<Personaje> inventarioCargadoCSV = new Inventario<>();
            inventarioCargadoCSV.setItems(Inventario.cargarPersonajesCSV(archivoCSV));
            System.out.println("Personajes cargados desde archivo CSV: ");
            inventarioCargadoCSV.paraCadaElemento(System.out::println);

        
    }
    public static void separador(){
        System.out.println("---------------------------------------");
    }
}
