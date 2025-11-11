import Clases.Consecionaria;
import Clases.Inventario;
import Clases.Vehiculo;
import usoJson.GestionJson;

public class Main {
    public static void main(String[] args) {

        Consecionaria consecionaria = GestionJson.mapConsecionaria();
        System.out.println(consecionaria);
        consecionaria.ejecutaMenu();

    }

}
