package Clases;

import java.util.List;

public class Auto extends Vehiculo {


    public Auto(String marca, String color, String modelo, double precio, Motor motor, List<String> descripcion) {
        super(marca, color, modelo, precio, motor, descripcion);
    }

    @Override
    public void financiacion() {

    }
}
