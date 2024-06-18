import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Persona {

    private Integer id;
    private String nombre;
    private int edad;
    private Domicilio domicilio;

    public Persona() {
    }

    public Persona(Integer id, String nombre, int edad, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.domicilio = domicilio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", domicilio=" + domicilio +
                '}';
    }

    public void toJson(String nombreArchivo) {
        try {
            // Leer contenido existente
            String contenido = JsonUtiles.leer(nombreArchivo);
            JSONObject mainJsonObject;

            if (contenido.isEmpty()) {
                mainJsonObject = new JSONObject();
                mainJsonObject.put("personas", new JSONArray()); // Inicializar el arreglo de personas si está vacío
            } else {
                mainJsonObject = new JSONObject(contenido);
                if (!mainJsonObject.has("personas")) {
                    mainJsonObject.put("personas", new JSONArray()); // Inicializar el arreglo de personas si no existe
                }
            }

            // Crear el JSON de la persona actual
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getId());
            jsonObject.put("nombre", getNombre());
            jsonObject.put("edad", getEdad());

            // Crear el JSON del domicilio
            JSONObject jsonDomicilio = new JSONObject();
            jsonDomicilio.put("calle", getDomicilio().getCalle());
            jsonDomicilio.put("altura", getDomicilio().getAltura());

            jsonObject.put("domicilio", jsonDomicilio);

            // Agregar la persona al array de personas
            mainJsonObject.getJSONArray("personas").put(jsonObject);

            // Guardar el objeto en el archivo
            JsonUtiles.grabar(mainJsonObject, nombreArchivo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static Persona fromJson(JSONObject jsonObject) throws JSONException {
        Integer id = jsonObject.getInt("id");
        String nombre = jsonObject.getString("nombre");
        int edad = jsonObject.getInt("edad");

        // Levantar el objeto de domicilio
        JSONObject jsonDomicilio = jsonObject.getJSONObject("domicilio");
        String calle = jsonDomicilio.getString("calle");
        int altura = jsonDomicilio.getInt("altura");
        Domicilio domicilio = new Domicilio(calle, altura);

        return new Persona(id, nombre, edad, domicilio);
    }


}
