package Servicio;


@FunctionalInterface
public interface Filtradora<T> {
    boolean filtrar(T o);
}
