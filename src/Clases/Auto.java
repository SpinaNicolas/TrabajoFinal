package Clases;

import java.util.List;

public class Auto extends Vehiculo {


    public Auto(String marca, String color, String modelo, double precio, Motor motor, List<String> descripcion, int ano) {
        super(marca, color, modelo, precio, motor, descripcion, ano);
    }


    public Auto() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public void financiacion() {

    }
}
