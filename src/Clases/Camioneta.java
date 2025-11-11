package Clases;

import Interfaces.Financiamiento;

import java.util.List;

public class Camioneta extends Vehiculo implements Financiamiento {


    public Camioneta(String tipo, int kms, int id, String marca, String color, String modelo, int ano, double precio, Motor motor, List<String> descripcion) {
        super(tipo, kms, id, marca, color, modelo, ano, precio, motor, descripcion);
    }

    public Camioneta() {

    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void calcularFinanciamiento() {

    }
}
