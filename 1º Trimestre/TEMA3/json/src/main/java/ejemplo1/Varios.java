/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2025 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */

package ejemplo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Varios {
  public static JsonValue leeJSON(String ruta) {
    try {
      if (ruta.toLowerCase().startsWith("http://")) {
        return leerHttp(ruta);
      } else if (ruta.toLowerCase().startsWith("https://")) {
        return leerHttps(ruta);
      } else {
        return leerFichero(ruta);
      }
    } catch (IOException e) {
      System.out.println("Error procesando documento Json " +
          e.getLocalizedMessage());
      return null;
    }
  }

  public static JsonValue leerFichero(String ruta) throws FileNotFoundException {
    try (JsonReader reader = Json.createReader(new FileReader(ruta))) {
      return reader.read();
      /*
       * JsonStructure jsonSt = reader.read();
       * System.out.println(jsonSt.getValueType());
       * JsonObject jsonObj = reader.readObject();
       * System.out.println(jsonObj.getValueType());
       * JsonArray jsonArr = reader.readArray();
       * System.out.println(jsonArr.getValueType());
       */
    }
  }

  public static JsonValue leerHttp(String direccion) throws IOException {
    URL url = new URL(direccion);
    try (InputStream is = url.openStream();
        JsonReader reader = Json.createReader(is)) {
      return reader.read();
    }
  }

  public static JsonValue leerHttps(String direccion) throws IOException {
    URL url = new URL(direccion);
    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    try (InputStream is = conn.getInputStream();
        JsonReader reader = Json.createReader(is)) {
      return reader.read();
    } finally {
      conn.disconnect();
    }
  }

  public static void escribeJSON(JsonValue json, File f) throws FileNotFoundException {
    System.out.println("Guardando tipo: " + json.getValueType());
    PrintWriter pw = new PrintWriter(f);
    JsonWriter writer = Json.createWriter(pw);
    // writer.write((JsonStructure) json);
    if (json.getValueType() == JsonValue.ValueType.OBJECT) {
      writer.writeObject(json.asJsonObject());
      // writer.writeObject((JsonObject)json);
    } else if (json.getValueType() == JsonValue.ValueType.ARRAY) {
      writer.writeArray(json.asJsonArray());
      // writer.writeArray((JsonArray)json);
    } else
      System.out.println("No se soporta la escritura");
    writer.close();
  }

  public static void navegarPelis() {
    // JsonValue j = (JsonArray) leeJSON("src\\main\\java\\resources\\pelis.json");
    // // System.out.println(j);
    // JsonArray raiz = j.asJsonArray();
    // System.out.println("Numero de pelis:" + raiz.size());
    JsonArray raiz = creaArray();
    for (JsonValue peli : raiz) {
      JsonObject p = peli.asJsonObject();
      // metemos la clave titulo y obtenemos su valor
      System.out.printf("Titulo:%s, Año:%d\n", p.getString("titulo"), p.getInt("año"));
      JsonArray interpretes = p.getJsonArray("interpretes");
      System.out.println("Interpretes:");
      for (JsonValue interprete : interpretes) {
        JsonObject inter = interprete.asJsonObject();
        System.out.println(inter.getString("nombre"));
        System.out.printf("Fecha de nacimiento: año - %d, mes - %d\n",
            inter.getJsonObject("fechaNacimiento").getInt("año"), inter.getJsonObject("fechaNacimiento").getInt("mes"));
      }
    }
  }

  public static JsonArray creaArray() {
    JsonArray array = (JsonArray) Json.createArrayBuilder()
        .add(Json.createObjectBuilder()
            .add("titulo", "El atlas de las nubes")
            .add("año", 2012)
            .add("directores", "Lana Wachowski, Tom Tykwer, Lilly Wachowski")
            .add("interpretes", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("nombre", "Tom Hanks")
                    .add("fechaNacimiento", Json.createObjectBuilder()
                        .add("año", 1956)
                        .add("mes", 8)))
                .add(Json.createObjectBuilder()
                    .add("nombre", "Halle Berry")
                    .add("fechaNacimiento", Json.createObjectBuilder()
                        .add("año", 1966)
                        .add("mes", 7)))))
        .add(Json.createObjectBuilder()
            .add("titulo", "La red social")
            .add("año", 2010)
            .add("directores", "David Fincher")
            .add("interpretes", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("nombre", "Jesse Eisenberg")
                    .add("fechaNacimiento", Json.createObjectBuilder()
                        .add("año", 1983)
                        .add("mes", 9)))
                .add(Json.createObjectBuilder()
                    .add("nombre", "Andrew Garfield")
                    .add("fechaNacimiento", Json.createObjectBuilder()
                        .add("año", 1983)
                        .add("mes", 7)))))
        .build();
    return array;
  }

  public static void generaEndisco(File f) throws FileNotFoundException {
    JsonGenerator generator = Json.createGenerator(new FileOutputStream(f));
    generator.writeStartArray()
        .writeStartObject()
        .write("titulo", "El atlas de las nubes")
        .write("año", 2012)
        .write("directores", "Lana Wachowski, Lilly Wachowski")
        .writeStartArray("intepretes")
        .writeStartObject()
        .write("nombre", "Tom Hanks")
        .writeStartObject("fechaNacimiento")
        .write("año", "1956")
        .write("mes", 8)
        .writeEnd()
        .writeEnd()
        .writeStartObject()
        .write("nombre", "Halle Berry")
        .writeStartObject("fechaNacimiento")
        .write("año", "1966")
        .write("mes", 7)
        .writeEnd()
        .writeEnd()
        .writeEnd()
        .writeEnd()
        .writeEnd()
        .close();
  }

  public static JsonValue ejercicio1() {
    String ciudad = "ourense";
    JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad
        + ",es&lang=es&units=metric&APPID=8f8dccaf02657071004202f05c1fdce0");
    return j;
  }

  public static JsonValue ejercicio2(double lat, double lon) {
    JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
        + "&APPID=8f8dccaf02657071004202f05c1fdce0");
    return j;
  }

  public static JsonValue ejercicio3(double lat, double lon, int x) {
    JsonValue j = leeJSON("http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=" + x
        + "&APPID=a975f935caf274ab016f4308ffa23453");
    return j;
  }

  public static int ejercicio4(JsonObject jo) {
    int id;
    id = jo.getInt("id");
    return id;
  }

  public static String ejercicio5(JsonObject jo) {
    String nombre;
    nombre = jo.getString("name");
    return nombre;
  }

  public static double[] ejercicio6(JsonObject jo) {
    JsonObject coord = jo.getJsonObject("coord");
    double lat = coord.getJsonNumber("lat").doubleValue();
    double lon = coord.getJsonNumber("lon").doubleValue();
    double[] coordenadas = { lat, lon };
    return coordenadas;
  }

  public static String ejercicio7(JsonObject jo){
    long fecha = jo.getInt("dt");

    JsonObject main = jo.getJsonObject("main");

    double temp = main.getJsonNumber("temp").doubleValue();

    int humedad = main.getInt("humidity");
    
    JsonObject nubes = jo.getJsonObject("clouds");

    int prob_nubes = nubes.getInt("all");

    JsonObject viento = jo.getJsonObject("wind");
    double velocidad = viento.getJsonNumber("speed").doubleValue();

    JsonArray tiempo = jo.getJsonArray("weather");
    JsonObject pronostico = tiempo.getJsonObject(0);
    String descripcion = pronostico.getString("description");

    return String.format("Fecha: %s, Tº: %f, humedad: %d, porc.nubes: %d, vel.viento: %f, pronostico: %s", unixTimeToString(fecha), temp, humedad, prob_nubes, velocidad, descripcion);
  }
  public static void ejercicio8(JsonObject jo){
    long fecha = jo.getInt("dt");

    JsonObject main = jo.getJsonObject("main");

    double temp = main.getJsonNumber("temp").doubleValue();

    int humedad = main.getInt("humidity");
    
    JsonObject nubes = jo.getJsonObject("clouds");

    int prob_nubes = nubes.getInt("all");

    JsonObject viento = jo.getJsonObject("wind");
    double velocidad = viento.getJsonNumber("speed").doubleValue();

    JsonArray tiempo = jo.getJsonArray("weather");
    JsonObject pronostico = tiempo.getJsonObject(0);
    String descripcion = pronostico.getString("description");

    System.out.printf("Fecha: %s, Tº: %f, humedad: %d, porc.nubes: %d, vel.viento: %f, pronostico: %s", unixTimeToString(fecha), temp, humedad, prob_nubes, velocidad, descripcion);
  }

  public static String unixTimeToString(long unixTime) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
  }

  public static void main(String[] args) throws FileNotFoundException {
    // JsonValue json = leeJSON("https://pokeapi.co/api/v2/pokemon/ditto");
    // escribeJSON(json, new File("src\\main\\java\\resources\\ditto.json"));
    // navegarPelis();
    // generaEndisco(new File("src\\main\\java\\resources\\pelisgenerado.json"));

    JsonValue j = ejercicio1();
    System.out.println("EJERCICIO 1");
    System.out.println(j);
    
    JsonValue j2 = ejercicio2(42.232819, -8.72264);
    System.out.println("EJERCICIO 2");
    System.out.println(j2);
    
    JsonValue j3 = ejercicio3(42.232819, -8.72264, 1);
    System.out.println("EJERCICIO 3");
    System.out.println(j3);
    
    JsonObject jo1 = j.asJsonObject();
    System.out.println("EJERCICIO 4");
    System.out.println(ejercicio4(jo1));
    
    System.out.println("EJERCICIO 5");
    System.out.println(ejercicio5(jo1));
    
    System.out.println("EJERCICIO 6");
    System.out.println(ejercicio6(jo1));
    
    System.out.println("EJERCICIO 7");
    System.out.println(ejercicio7(jo1));
    
    System.out.println("EJERCICIO 8");
    JsonObject jo3 = j3.asJsonObject();
    ejercicio8(jo3);
https://prod.liveshare.vsengsaas.visualstudio.com/join?CB774C049E3004F7C7BD3514E6BC20C55BCF
  }
}
