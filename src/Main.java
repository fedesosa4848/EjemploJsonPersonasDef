import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        String nombreArchivo = "personas";
       /* Persona aux;
        for (int i = 1; i <= 3; i++) {
            System.out.println("Ingrese los datos de la persona numero " + "(" + i + ")\n");
            aux = crearPersona();
            aux.toJson(nombreArchivo);
        }*/

        HashMap<Integer, Persona> hashMap = new HashMap<>();
        cargarPersonasDesdeJson(nombreArchivo,hashMap);
        // Mostrar personas cargadas
        for (Persona persona : hashMap.values()) {
            System.out.println(persona);
        }
    }


        public static Persona crearPersona () {
            System.out.println("Ingrese el id");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el nombre");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese la edad");
            int edad = scanner.nextInt();

            System.out.println("Datos Domicilio:");
            scanner.nextLine();
            System.out.println("Ingrese la calle");
            String calle = scanner.nextLine();
            System.out.println("Ingrese la altura");
            int altura = scanner.nextInt();
            Domicilio aux = new Domicilio(calle, altura);

            return new Persona(id, nombre, edad, aux);
        }

        public static void cargarPersonasDesdeJson (String archivoJson, HashMap < Integer, Persona > personas){
            try {
                String contenido = JsonUtiles.leer(archivoJson);
                JSONObject jsonObject = new JSONObject(contenido);
                JSONArray jsonArray = jsonObject.getJSONArray("personas");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject personaObj = jsonArray.getJSONObject(i);
                    Persona persona = Persona.fromJson(personaObj);
                    personas.put(persona.getId(), persona);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
