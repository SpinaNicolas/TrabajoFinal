package Clases;

import usoJson.GestionJson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Inventario <T extends Vehiculo>{
    Scanner scanner = new Scanner(System.in);
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


    public void eliminarVehiculo (){
        int id;
        System.out.println("Ingrese id del vehiculo a eliminar: ");
        id = scanner.nextInt();
        Iterator<T> iterator = lista.iterator();
        while(iterator.hasNext())
        {
            if(iterator.next().equals(id)){
                iterator.remove();
                System.out.println("Vehiculo eliminado correctamente.");
            }
            else {
                System.out.println("No se encuentra el vehiculo.");
            }
        }
    }

    @Override
    public String toString() {
        return  lista + "\n" +
                "}";
    }

    public void agregarVehiculo(T vehiculo) {
        lista.add(vehiculo);
        GestionJson.actualizaJson(vehiculo);
    }

    public List<T> cargaVehiculoEnLista () {
        int opcion;
        System.out.println("1. Nuevo auto \n" +
                "2. Nueva camioneta \n" +
                "3. Nueva moto \n");
        opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                Vehiculo v = new Auto();
                v = creaVehiculo(v);
                agregarVehiculo((T) v);
                break;

            case 2:
                Vehiculo c = new Camioneta();
                c = creaVehiculo(c);
                agregarVehiculo((T) c);
                break;
            case 3:
                Vehiculo m = new Camioneta();
                m = creaVehiculo(m);
                agregarVehiculo((T) m);
                break;
        }
        return lista;
    }

    public Vehiculo creaVehiculo (Vehiculo v){
        Motor motor = new Motor();
        ArrayList<String> descripcion = new ArrayList<>();
        scanner.nextLine();
        System.out.println("Ingrese color");/// Excepcion si no es string
        v.setColor(scanner.nextLine());
        System.out.println("Ingrese marca"); /// Excepcion si no es string
        v.setMarca(scanner.nextLine());
        System.out.println("Ingrese modelo");/// Excepcion si no es string
        v.setModelo(scanner.nextLine());
        System.out.println("Ingrese año");/// Excepcion si es menos q 0
        v.setAno(scanner.nextInt());
        System.out.println("Ingrese precio");/// Excepcion si es menos q 0
        v.setPrecio(scanner.nextDouble());
        /// MOTOR
        scanner.nextLine();
        System.out.println("Ingrese numero de cilindros de motor");/// Excepcion si no ingresa numero
        motor.setCilindros(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Ingrese tipo de motor (Nafta/Diesel)");/// excepcion si ingresa otra cosa
        motor.setTipo(scanner.nextLine());
        System.out.println("Ingrese potencia del motor");/// Excepcion si no ingresa numero
        motor.setPotencia(scanner.nextDouble());
        v.setMotor(motor);
        /// ESPECIFICACIONES
        int seguir = 0;
        do {
            scanner.nextLine();
            String s;
            System.out.println("Ingrese especificacion:");
            s = scanner.nextLine();
            descripcion.add(s);
            System.out.println("Presione 1 para cargar otra especificacion, 0 para no cargar");  /// Excepcion si seguir menor q 0 o mayor q 1 (opicnones valids)
            seguir = scanner.nextInt();
        } while (seguir==1);

        v.setDescripcion(descripcion);

        return v;
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

    /// listarStock osea solo los disponibles.
    /// listarHistorial osea disponibles y no ?
    /// listarVendido osea no dispo
}
