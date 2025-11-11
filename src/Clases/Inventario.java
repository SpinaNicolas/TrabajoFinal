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

    public Vehiculo creaVehiculo(Vehiculo v) {
        Motor motor = new Motor();
        ArrayList<String> descripcion = new ArrayList<>();

        scanner.nextLine(); // Limpieza de buffer

        System.out.println("Ingrese ID del vehículo"); // Validar que sea positivo
        v.setId(scanner.nextInt());

        scanner.nextLine();
        System.out.println("Ingrese tipo de vehículo (auto/camioneta/moto)"); // Validar que sea uno de los tres
        v.setTipo(scanner.nextLine());

        System.out.println("Ingrese color"); // Validar que no esté vacío
        v.setColor(scanner.nextLine());

        System.out.println("Ingrese marca"); // Validar que no esté vacío
        v.setMarca(scanner.nextLine());

        System.out.println("Ingrese modelo"); // Validar que no esté vacío
        v.setModelo(scanner.nextLine());

        System.out.println("Ingrese año"); // Validar que sea > 0
        v.setAno(scanner.nextInt());

        System.out.println("Ingrese kilometraje"); // Validar que sea >= 0
        v.setKms(scanner.nextInt());

        System.out.println("Ingrese precio"); // Validar que sea >= 0
        v.setPrecio(scanner.nextDouble());

        // MOTOR
        scanner.nextLine();
        System.out.println("Ingrese número de cilindros del motor"); // Validar que sea > 0
        motor.setCilindros(scanner.nextInt());

        scanner.nextLine();
        System.out.println("Ingrese tipo de motor (nafta/diesel)"); // Validar que sea uno de los dos
        motor.setTipo(scanner.nextLine());

        System.out.println("Ingrese potencia del motor"); // Validar que sea > 0
        motor.setPotencia(scanner.nextInt());

        v.setMotor(motor);

        // ESPECIFICACIONES
        int seguir = 0;
        do {
            scanner.nextLine();
            System.out.println("Ingrese especificación:");
            String s = scanner.nextLine();
            descripcion.add(s);

            System.out.println("Presione 1 para cargar otra especificación, 0 para finalizar"); // Validar que sea 0 o 1
            seguir = scanner.nextInt();
        } while (seguir == 1);

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
    public void menu() {
        System.out.println("Bienvenido a Garage Mardel");

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===== MENÚ CONCESIONARIA =====");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Mostrar vehículos");
            System.out.println("3. Buscar vehículo por marca");
            System.out.println("4. Filtrar tipos de vehiculos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println(">> Agregar vehículo");
                    cargaVehiculoEnLista();
                    break;
                case 2:
                    System.out.println(">> Mostrar vehiculos");
                    mostrarListaEntera();
                    break;
                case 3:
                    System.out.println(">> Buscar vehículo por marca");

                    break;
                case 4:
                    System.out.println(">> Que tipo de vehiculo quiere ver..");
                    System.out.println("1 .Auto");
                    System.out.println("2 .Moto");
                    System.out.println("3 .Camioneta");
                    mostrarPorTipo(scanner.nextInt());
                    break;
                case 5:
                    System.out.println(">> Saliendo del sistema...");
                    break;
                default:
                    System.out.println(">> Opción inválida. Intente nuevamente.");
            }


        } while (opcion != 5);

    }


    public void mostrarListaEntera() {
        System.out.println("=== INVENTARIO DE VEHÍCULOS ===");
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos en el inventario.");
        } else {
            for (Vehiculo v : lista) {
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.printf("║ 🆔 ID: %-38d ║\n", v.getId());
                System.out.printf("║ 🚘 Tipo: %-36s ║\n", v.getTipo());
                System.out.printf("║ 🚗 Marca: %-34s ║\n", v.getMarca());
                System.out.printf("║ 📌 Modelo: %-33s ║\n", v.getModelo());
                System.out.printf("║ 🎨 Color: %-34s ║\n", v.getColor());
                System.out.printf("║ 📅 Año: %-36d ║\n", v.getAno());
                System.out.printf("║ 🛣️ Kilometraje: %-26d km ║\n", v.getKms());
                System.out.printf("║ 💰 Precio: $%-31.2f ║\n", v.getPrecio());
                System.out.println("║ 🔧 Motor:                                     ║");
                System.out.printf("║   → Tipo: %-32s ║\n", v.getMotor().getTipo());
                System.out.printf("║   → Cilindros: %-27d ║\n", v.getMotor().getCilindros());
                System.out.printf("║   → Potencia: %-28d HP ║\n", v.getMotor().getPotencia());
                System.out.println("║ 📝 Descripción:                              ║");
                for (String desc : v.getDescripcion()) {
                    System.out.printf("║   - %-38s ║\n", desc);
                }
                System.out.println("╚══════════════════════════════════════════════╝\n");
            }
        }
    }
    public void mostrarPorTipo(int numero) {
        switch (numero) {
            case 1:
                listarAutos();
                break;
            case 2:
                listarMotos();
                break;
            case 3:
                listarCamioneta();
                break;
        }
    }
}


