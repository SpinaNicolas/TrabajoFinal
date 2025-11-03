package Clases;

import java.util.List;

public class Camioneta extends Vehiculo{


    public Camioneta(String marca, String color, String modelo, double precio, Motor motor, List<String> descripcion) {
        super(marca, color, modelo, precio, motor, descripcion);

    }

    public Camioneta() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void financiacion() {

    }
}
