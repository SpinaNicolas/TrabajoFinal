package Clases;

import java.util.List;

public abstract class Vehiculo  {

    private String marca;
    private String color;
    private String modelo;
    private int ano;
    private double precio;
    private Motor motor;
    private List<String> descripcion;

    public Vehiculo(String marca, String color, String modelo, double precio, Motor motor, List<String> descripcion, int ano) {
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.precio = precio;
        this.motor = motor;
        this.ano = ano;
        this.descripcion = descripcion;
    }

    public Vehiculo() {
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public List<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(List<String> descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void financiacion();

    @Override
    public String toString() {
        return "🚗 Vehículo en stock 🚗\n" +
                "→ Marca: " + marca + "\n" +
                "→ Modelo: " + modelo + "\n" +
                "→ Año: " + ano + "\n" +
                "→ Color: " + color + "\n" +
                "→ Precio: $" + precio + "\n" +
                "→ Descripción: " + descripcion + "\n" +
                "-----------------------------";
    }

}
