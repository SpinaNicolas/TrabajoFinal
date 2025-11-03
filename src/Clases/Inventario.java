package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventario <T extends Vehiculo>{

    private List<T> lista;

    public Inventario() {
        this.lista = new ArrayList<>();
    }

    public List<T> getLista() {
        return lista;
    }
    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public void eliminarVehiculo(T  vehiculo ) {
        Iterator<T> iterator = lista.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(vehiculo)){
                iterator.remove();
            }
        }
    }
    public void agregarVehiculo(T vehiculo) {
        lista.add(vehiculo);

    }

    public void listarMotos(){
        for(T vehiculo : lista){
            if(vehiculo instanceof Moto){
                System.out.println("Moto" + vehiculo);
            }
        }
    }
    public void listarAutos(){
        for(T vehiculo : lista){
            if(vehiculo instanceof Auto){
                System.out.println("Auto" + vehiculo);
            }
        }
    }

    public void listarCamioneta(){
        for(T vehiculo : lista){
            if(vehiculo instanceof Camioneta){
                System.out.println("Camioneta" + vehiculo);
            }
        }
    }


}
