package Clases;

import java.util.ArrayList;
import java.util.List;

public class Inventario <T extends Vehiculo>{

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

}
