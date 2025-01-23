import org.json.JSONObject;

import java.io.*;
import java.util.Properties;

public class Datos {
    private static final String Datos_APIFixer = "datos.properties";
    private static final String LLAVE_DIVISAS = "rates";
    JSONObject json;

    public static void guardarDivisas(String json) {
        Properties propiedades = new Properties();
        try (FileOutputStream fos = new FileOutputStream(Datos_APIFixer)) {
            propiedades.setProperty(LLAVE_DIVISAS, json);
            propiedades.store(fos, null);
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public static String obtenerDivisas() {
        Properties propiedades = new Properties();
        try(FileInputStream fis = new FileInputStream(Datos_APIFixer)) {
            propiedades.load(fis);
            return propiedades.getProperty(LLAVE_DIVISAS);
        } catch (IOException e) {
            System.err.println("Error al leer los datos: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
    //Simula la consulta a la API y obtiene el JSON
    String json = "{\"divisas\": [{\"nombre\": \"USD\", \"valor\": 1.20}," +
            "{\"nombre\": \"EUR\": \"valor\": 1.10}]}";
    //TODO: hacer la consulta, obtener el JSON y guardarlo.
    String prueba = "{\"rates\": [{\"USD\": 1.032}, {\"EUR\": 4482}]}";
    // Guarda el JSON en el archivo
        guardarDivisas(prueba);

    // Obtiene el JSON almacenado
    String jsonAlmacenado = obtenerDivisas();
    System.out.println(jsonAlmacenado);
    }
}
