package Clases;

import Excepciones.errorNumerico;
import Excepciones.errorTipoDato;
import usoJson.GestionJson;

import java.util.*;

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


    public void adquirirVehiculo (){
        System.out.println(">> Que tipo de vehiculo quiere adquirir? ");
        System.out.println("1 .Auto");
        System.out.println("2 .Moto");
        System.out.println("3 .Camioneta");
        mostrarPorTipo(scanner.nextInt());
        int id;
        System.out.println("Ingrese id del vehiculo a adquirir: ");
        id = scanner.nextInt();
        Iterator<T> iterator = lista.iterator();
        boolean valido = false;
        while(iterator.hasNext())
        {
            T vehiculo = iterator.next();
            if (vehiculo.getId() == id) {
            valido = true;
                iterator.remove();
                System.out.println("Vehiculo adquirido correctamente.");
            }
        }
        if (valido == false){
            System.out.println("No se encuentra el vehiculo.");
        }
    }

    @Override
    public String toString() {
        return  lista + "\n" +
                "}";
    }
    public List<T> ordenVehiculosPrecio (){
        Collections.sort(lista);
        return lista;
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
                Vehiculo a = new Auto();
                a = creaVehiculo(a);
                a.setTipo("auto");
                agregarVehiculo((T) a);
                break;

            case 2:
                Vehiculo c = new Camioneta();
                c = creaVehiculo(c);
                c.setTipo("camioneta");
                agregarVehiculo((T) c);
                break;
            case 3:
                Vehiculo m = new Moto();
                m = creaVehiculo(m);
                m.setTipo("moto");
                agregarVehiculo((T) m);
                break;
        }
        return lista;
    }

    public Vehiculo creaVehiculo(Vehiculo v) throws errorNumerico, errorTipoDato {
        Motor motor = new Motor();
        ArrayList<String> descripcion = new ArrayList<>();

        System.out.println("Ingrese ID del vehículo");
        int id;
        if (scanner.hasNextInt()) {
            id = scanner.nextInt();
            scanner.nextLine();
            if (id < 0) {
                throw new errorNumerico("Id no puede ser negativo.");
            }
            v.setId(id);
        }
        else {
            throw new errorTipoDato("El id debe ser de tipo entero.");
        }
        
        System.out.println("Ingrese color");
        String color = scanner.nextLine();
        if (color.matches("\\d+")) {
            throw new errorTipoDato("Color no puede ser un numero");
        }else {
            v.setColor(color);
        }


        System.out.println("Ingrese marca");
        String marca = scanner.nextLine();
        if (marca.matches("\\d+")) {
            throw new errorTipoDato("Marca no puede ser un numero");
        }else {
            v.setMarca(marca);
        }

        System.out.println("Ingrese modelo");
        String modelo = scanner.nextLine();
        if (modelo.matches("\\d+")) {
            throw new errorTipoDato("Modelo no puede ser un numero");
        }else {
            v.setModelo(modelo);
        }

        System.out.println("Ingrese año");
        int ano = scanner.nextInt();
        if (ano < 0 || ano > 2025){
            throw new errorNumerico("Año no puede ser negativo o mayor al actual.");
        } else {
            v.setAno(ano);
        }

        System.out.println("Ingrese kilometraje");
        int kms = scanner.nextInt();
        if (kms < 0){
            throw new errorNumerico("Los kms de un vehiculo no pueden ser negativos.");
        } else {
            v.setKms(kms);
        }


        System.out.println("Ingrese precio");
        double precio = scanner.nextDouble();
        if (precio < 0){
            throw new errorNumerico("El precio de un vehiculo no puede ser negativo.");
        }
        v.setPrecio(precio);

        // MOTOR
        scanner.nextLine();
        System.out.println("Ingrese número de cilindros del motor");
        int cilindros = scanner.nextInt();
        if (cilindros < 2 || cilindros > 16){
            throw new errorNumerico("Un coche no puede tener menos de 2 cilindros .");
        }else {
            motor.setCilindros(cilindros);
        }


        scanner.nextLine();
        System.out.println("Ingrese tipo de motor (nafta/diesel)");
        String tipo = scanner.nextLine();
        if (!tipo.equalsIgnoreCase("nafta") && !tipo.equalsIgnoreCase("diesel")) {
            throw new errorTipoDato("Tipo de motor no valido, debe ser 'nafta' o 'diesel'");
        }else {
            motor.setTipo(tipo);
        }

        System.out.println("Ingrese potencia del motor");
        int potencia = scanner.nextInt();
        if (potencia<0){
            throw new errorNumerico("La potencia no puede ser menor a 0.");
        }else {
            motor.setPotencia(potencia);
        }

        int seguir = 0;
        do {
            scanner.nextLine();
            System.out.println("Ingrese especificación:");
            String s = scanner.nextLine();
            descripcion.add(s);

            System.out.println("Presione 1 para cargar otra especificación, 0 para finalizar");
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
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║           🚘 MENÚ DE CONCESIONARIA           ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║ 1️⃣  Agregar vehículo                        ║");
            System.out.println("║ 2️⃣  Mostrar vehículos                       ║");
            System.out.println("║ 3️⃣  Mostrar tipos de vehículos              ║");
            System.out.println("║ 4️⃣  Ordenar vehículos por precio            ║");
            System.out.println("║ 5️⃣  Ver opciones de financiamiento          ║");
            System.out.println("║ 6️⃣  Filtrar por marca                       ║");
            System.out.println("║ 7️⃣  Filtrar por kilómetros                  ║");
            System.out.println("║ 8️⃣  Adquirir vehículo                       ║");
            System.out.println("║ 0️⃣  Salir                                   ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("📌 Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(">> Agregar vehiculo");
                    cargaVehiculoEnLista();
                    break;
                case 2:
                    System.out.println(">> Mostrar vehiculos");
                    mostrarListaEntera();
                    break;
                case 3:
                    System.out.println(">> Que tipo de vehiculo quiere ver..");
                    System.out.println("1 .Auto");
                    System.out.println("2 .Moto");
                    System.out.println("3 .Camioneta");
                    mostrarPorTipo(scanner.nextInt());
                    break;
                case 4:
                    System.out.println(">> Ordenar por precio");
                    ordenVehiculosPrecio();
                    break;
                case 5:
                    muestraFinanciacion();
                    break;
                case 6:
                    filtrarXmarca();
                    break;
                case 7:
                    filtraKms();
                    break;
                case 8:
                    adquirirVehiculo();
                    break;
                case 0:
                    System.out.println(">> Saliendo del sistema...");
                    break;
                default:
                    System.out.println(">> Opción inválida. Intente nuevamente.");
            }


        } while (opcion != 0);

    }


    public void mostrarListaEntera() {
        System.out.println("=== INVENTARIO DE VEHÍCULOS ===");
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos en el inventario.");
        } else {
            for (Vehiculo v : lista) {
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.printf(" 🆔 ID: %-38d \n", v.getId());
                System.out.printf(" 🚘 Tipo: %-36s \n", v.getTipo());
                System.out.printf(" 🚗 Marca: %-34s \n", v.getMarca());
                System.out.printf(" 📌 Modelo: %-33s \n", v.getModelo());
                System.out.printf(" 🎨 Color: %-34s \n", v.getColor());
                System.out.printf(" 📅 Año: %-36d \n", v.getAno());
                System.out.printf(" 🛣️ Kilometraje: %d km\n", v.getKms());
                System.out.printf(" 💰 Precio: $%-31.2f \n", v.getPrecio());
                System.out.println(" 🔧 Motor:                                     ");
                System.out.printf(" → Tipo: %-32s \n", v.getMotor().getTipo());
                System.out.printf(" → Cilindros: %-27d \n", v.getMotor().getCilindros());
                System.out.printf(" → Potencia: %.0f HP\n", v.getMotor().getPotencia());
                System.out.println(" 📝 Descripción:                              ");
                for (String desc : v.getDescripcion()) {
                    System.out.printf("   - %-38s \n", desc);
                }
                System.out.println("╚════════════════════════════════════════════════════════╝\n");
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

    public void muestraFinanciacion (){
        System.out.println("=== PLAN DE FINANCIACIÓN DE VEHÍCULOS ===");
        System.out.println("Seleccione el tipo de vehículo a listar:");
        System.out.println("1 - Autos");
        System.out.println("2 - Camionetas");
        System.out.println("3 - Motos");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        String seleccion = "";
        if (opcion == 1) {
            seleccion = "auto";
        } else if (opcion == 2) {
            seleccion = "camioneta";
        } else if (opcion == 3) {
            seleccion = "moto";
        } else {
            System.out.println("Opción inválida. Solo se aceptan valores entre 1 y 3.");
        }

        for (T v : lista) {
            if (seleccion.equalsIgnoreCase("auto") && v instanceof Auto) {
                v.calcularFinanciamiento();
            } else if (seleccion.equalsIgnoreCase("camioneta") && v instanceof Camioneta) {
                v.calcularFinanciamiento();
            } else if (seleccion.equalsIgnoreCase("moto") && v instanceof Moto) {
                v.calcularFinanciamiento();
            }
        }
    }

    public String validaTipo() {
        String tipo;
        boolean valido = false;

        do {
            tipo = scanner.nextLine();

            if (tipo.equalsIgnoreCase("Auto") || tipo.equalsIgnoreCase("Moto") || tipo.equalsIgnoreCase("Camioneta")) {
                valido = true;
            } else {
                System.out.println("❌ ERROR: Tipo de vehículo inválido. Intente nuevamente.");
            }
        } while (!valido);


        return tipo;
    }

    public void filtraKms(){

        System.out.println("Ingrese tope de kms:");
        int kms = scanner.nextInt();
        for (Vehiculo v : lista) {
            if (v.getKms()<= kms) {
                System.out.println("────────────────────────────────────────────");
                System.out.println(v);
            }
        }
    }

    public void filtrarXmarca() {
        System.out.print("Ingrese una marca para buscar: ");
        String marca = scanner.nextLine();
        boolean encontrada = false;

        for (Vehiculo v : lista) {
            if (v.getMarca().equalsIgnoreCase(marca)) {
                encontrada = true;
                System.out.println("────────────────────────────────────────────");
                System.out.println(v);
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron vehículos con la marca '" + marca + "'.");
        }
    }
}



