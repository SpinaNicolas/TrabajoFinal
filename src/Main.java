import Clases.Consecionaria;
import Clases.Inventario;
import Clases.Vehiculo;
import usoJson.GestionJson;

public class Main {
    public static void main(String[] args) {

        /// crear conce
        Inventario<Vehiculo>inventario = new Inventario<>();
        inventario.cargaVehiculoEnLista();
        //Consecionaria consecionaria = new Consecionaria("Garage Mardel", inventario);
        //System.out.println(consecionaria);
        /// CREO JSON
        //GestionJson.toJson(consecionaria);
        System.out.println("holi");
    }
}
