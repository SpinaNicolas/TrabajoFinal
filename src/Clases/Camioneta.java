package Clases;

import java.util.List;

public class Camioneta extends Vehiculo{
    private Boolean siOno4x4;

    public Camioneta(String marca, String color, String modelo, double precio, Motor motor, List<String> descripcion, Boolean siOno4x4) {
        super(marca, color, modelo, precio, motor, descripcion);
        this.siOno4x4 = siOno4x4;
    }

    public Boolean getSiOno4x4() {
        return siOno4x4;
    }

    public void setSiOno4x4(Boolean siOno4x4) {
        this.siOno4x4 = siOno4x4;
    }

    @Override
    public void financiacion() {

    }
}
