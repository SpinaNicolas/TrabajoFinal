package Clases;

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

    public void eliminarVehiculo(T  vehiculo ) {
        Iterator<T> iterator = lista.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(vehiculo)){
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "Inventario {\n" +
                "  Lista: " + lista + "\n" +
                "}";
    }

    public void agregarVehiculo(T vehiculo) {
        lista.add(vehiculo);

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
        /// actualiza json
        return lista;
    }

    public Vehiculo creaVehiculo (Vehiculo v){
        Motor motor = new Motor();
        ArrayList<String> descripcion = new ArrayList<>();
        scanner.nextLine();
        System.out.println("Ingrese color");
        v.setColor(scanner.nextLine());
        System.out.println("Ingrese marca");
        v.setMarca(scanner.nextLine());
        System.out.println("Ingrese modelo");
        v.setModelo(scanner.nextLine());
        System.out.println("Ingrese precio");
        v.setPrecio(scanner.nextDouble());
        /// MOTOR
        scanner.nextLine();
        System.out.println("Ingrese numero de cilindros de motor");/// Excepcion si no ingresa numero
        motor.setCilindros(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Ingrese tipo de motor (Nafta/Diesel)");
        motor.setTipo(scanner.nextLine());
        System.out.println("Ingrese potencia del motor");
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
            System.out.println("Presione 1 para cargar otra especificacion, 0 para no cargar");
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


}
