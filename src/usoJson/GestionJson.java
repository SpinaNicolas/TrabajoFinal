package usoJson;

import Clases.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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
                jVehiculo.put("id", vehiculo.getId());
                jVehiculo.put("tipo", vehiculo.getTipo());
                jVehiculo.put("kms", vehiculo.getKms());
                jVehiculo.put("marca", vehiculo.getMarca());
                jVehiculo.put("color", vehiculo.getColor());
                jVehiculo.put("modelo", vehiculo.getModelo());
                jVehiculo.put("ano", vehiculo.getAno());
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
            JSONObject jConsecionario = jArchi.getJSONObject("consecionaria");
            /// LISTA PARA ACTUALIZAR
            JSONArray jlista = jConsecionario.getJSONArray("listaVehiculos");
            /// VEHICULO JSON PARA CARGAR A LIST
            JSONObject jVehiculo = new JSONObject();
            jVehiculo.put("id", vehiculo.getId());
            jVehiculo.put("tipo", vehiculo.getTipo());
            jVehiculo.put("kms", vehiculo.getKms());
            jVehiculo.put("marca", vehiculo.getMarca());
            jVehiculo.put("color", vehiculo.getColor());
            jVehiculo.put("modelo", vehiculo.getModelo());
            jVehiculo.put("ano", vehiculo.getAno());
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


    /// MAPEO JSON

    public static Vehiculo mapVehiculo(JSONObject jVehiculo, Vehiculo vehiculo) {
        try {
            vehiculo.setId(jVehiculo.getInt("id"));
            vehiculo.setTipo(jVehiculo.getString("tipo"));
            vehiculo.setKms(jVehiculo.getInt("kms"));
            vehiculo.setMarca(jVehiculo.getString("marca"));
            vehiculo.setColor(jVehiculo.getString("color"));
            vehiculo.setModelo(jVehiculo.getString("modelo"));
            vehiculo.setAno(jVehiculo.getInt("ano"));
            vehiculo.setPrecio(jVehiculo.getDouble("precio"));

            // Motor
            JSONObject jMotor = jVehiculo.getJSONObject("motor");
            Motor motor = new Motor();
            motor.setCilindros(jMotor.getInt("cilindros"));
            motor.setPotencia(jMotor.getInt("potencia"));
            motor.setTipo(jMotor.getString("tipo"));
            vehiculo.setMotor(motor);

            // Descripciones
            JSONArray jDesc = jVehiculo.getJSONArray("descripcion");
            ArrayList<String> descripciones = new ArrayList<>();
            for (int i = 0; i < jDesc.length(); i++) {
                descripciones.add(jDesc.getString(i));
            }
            vehiculo.setDescripcion(descripciones);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return vehiculo;
    }

    public static ArrayList<Vehiculo> mapArregloVehiculos(JSONArray jVehiculos) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        for (int i = 0; i < jVehiculos.length(); i++) {

            try {
                JSONObject jVehiculo = jVehiculos.getJSONObject(i);
                if (jVehiculo.getString("tipo").equals("auto")) {
                    Vehiculo a = new Auto();
                    a = mapVehiculo(jVehiculo, a);
                    vehiculos.add(a);
                } else if (jVehiculo.getString("tipo").equals("camioneta")) {
                    Vehiculo c = new Camioneta();
                    c = mapVehiculo(jVehiculo, c);
                    vehiculos.add(c);
                } else {
                    Vehiculo m = new Moto();
                    m = mapVehiculo(jVehiculo, m);
                    vehiculos.add(m);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        return vehiculos;
    }
    public static Consecionaria mapConsecionaria() {
        Consecionaria consecionaria = new Consecionaria();
        Inventario<Vehiculo> inventario = new Inventario<>();

        try {
            JSONObject jArchivo = new JSONObject(JSONUtiles.leer("consecionario.json"));
            JSONObject jConcesionaria = jArchivo.getJSONObject("consecionaria");

            consecionaria.setNombre(jConcesionaria.getString("nombre"));

            JSONArray jVehiculos = jConcesionaria.getJSONArray("listaVehiculos");
            inventario.setLista(mapArregloVehiculos(jVehiculos));

            consecionaria.setVehiculos(inventario);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return consecionaria;
    }
}
