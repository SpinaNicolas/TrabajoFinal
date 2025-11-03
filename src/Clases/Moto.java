package Clases;

import java.util.List;

public class Moto extends Vehiculo {


    public Moto(String marca, String color, String modelo, double precio, Motor motor, List<String> descripcion) {
        super(marca, color, modelo, precio, motor, descripcion);
    }

    public Moto() {
    }

    @Override
    public void financiacion() {

    }
}
