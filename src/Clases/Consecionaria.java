package Clases;

import java.util.ArrayList;

public class Consecionaria {
    private String nombre;
    private Inventario<Vehiculo> vehiculos;

    public Consecionaria(String nombre, Inventario<Vehiculo> vehiculos) {
        this.nombre = nombre;
        this.vehiculos = vehiculos;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Inventario<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    public void setVehiculos(Inventario<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public String toString() {
        return "Consecionaria{" +
                "nombre='" + nombre + '\'' +
                ", vehiculos=" + vehiculos +
                '}';
    }
}
