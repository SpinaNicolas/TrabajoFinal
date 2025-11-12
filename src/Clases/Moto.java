package Clases;

import Interfaces.Financiamiento;

import java.util.List;

public class Moto extends Vehiculo implements Financiamiento {

    public Moto(String tipo, int kms, int id, String marca, String color, String modelo, int ano, double precio, Motor motor, List<String> descripcion) {
        super(tipo, kms, id, marca, color, modelo, ano, precio, motor, descripcion);
    }

    public Moto() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void calcularFinanciamiento() {

        System.out.println("🚗 Vehículo: " + this.getMarca() + " " + this.getModelo());
        System.out.printf("💰 Precio total: $%.2f\n", this.getPrecio());
        System.out.printf("📥 Adelanto (40%%): $%.2f\n", this.getPrecio() * 0.4);
        System.out.printf("📈 Resto a financiar (60%%): $%.2f\n", this.getPrecio() * 0.6);
        System.out.println("────────────────────────────────────────────");
    }

}
