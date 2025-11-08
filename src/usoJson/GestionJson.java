package usoJson;

import Clases.Consecionaria;
import Clases.Vehiculo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class GestionJson {
    public static void toJson (Consecionaria c){
        JSONObject jConsecionaria = new JSONObject();
        JSONObject jArchivo = new JSONObject();
        try {
            jArchivo.put("consecionaria", jConsecionaria);
            jConsecionaria.put("nombre", c.getNombre());
            JSONArray jListaVehiculos = new JSONArray();

            for (Vehiculo vehiculo: c.getVehiculos().getLista()){
                JSONObject jVehiculo = new JSONObject();
                jVehiculo.put("marca", vehiculo.getMarca());
                jVehiculo.put("color", vehiculo.getColor());
                jVehiculo.put("modelo", vehiculo.getModelo());
                jVehiculo.put("precio", vehiculo.getPrecio());
                JSONObject jMotor = new JSONObject();

                jMotor.put("tipo", vehiculo.getMotor().getTipo());
                jMotor.put("cilindros", vehiculo.getMotor().getCilindros());
                jMotor.put("potencia", vehiculo.getMotor().getPotencia());

                jVehiculo.put("motor", jMotor);
                jVehiculo.put("descripcion", vehiculo.getDescripcion());
                jListaVehiculos.put(jVehiculo);
            }
           jConsecionaria.put("listaVehiculos", jListaVehiculos);
           JSONUtiles.grabar(jArchivo, "consecionario.json");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void actualizaJson (Vehiculo vehiculo){

        try {
            JSONObject jArchi = new JSONObject(JSONUtiles.leer("consecionario.json"));
            JSONObject jConsecionario = jArchi.getJSONObject("Consecionaria");
            /// LISTA PARA ACTUALIZAR
            JSONArray jlista = jConsecionario.getJSONArray("listaVehiculos");
            /// VEHICULO JSON PARA CARGAR A LIST
            JSONObject jVehiculo = new JSONObject();
            jVehiculo.put("marca", vehiculo.getMarca());
            jVehiculo.put("color", vehiculo.getColor());
            jVehiculo.put("modelo", vehiculo.getModelo());
            jVehiculo.put("precio", vehiculo.getPrecio());
            JSONObject jMotor = new JSONObject();

            jMotor.put("tipo", vehiculo.getMotor().getTipo());
            jMotor.put("cilindros", vehiculo.getMotor().getCilindros());
            jMotor.put("potencia", vehiculo.getMotor().getPotencia());

            jVehiculo.put("motor", jMotor);
            jVehiculo.put("descripcion", vehiculo.getDescripcion());
            jlista.put(jVehiculo);

            JSONUtiles.grabar(jArchi,"consecionario.json" );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
