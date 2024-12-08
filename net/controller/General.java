/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import model.DtoDatos;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yepez
 */
public class General {

    private final String api = "http://localhost/proyectos/Repaso/api.php";

    public List<DtoDatos> getDataProducto(String producto) {
        List<DtoDatos> datos = new ArrayList<>();

        try {
            URL url = new URL(api + "?producto=" + producto);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DtoDatos dato = new DtoDatos(
                            jsonObject.getString("bodega"),
                            jsonObject.getString("ciudad"),
                            jsonObject.getString("ubicacion"),
                            jsonObject.getInt("cantidad")
                    );
                    datos.add(dato);
                }
            } else {
                System.out.println("Error: Código de respuesta HTTP " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datos;
    }

    public boolean guardarProducto(int producto, int bodega, String ubicacion, int cantidad) {
        try {
            // Crear el objeto JSON con los datos
            JSONObject jsonData = new JSONObject();
            jsonData.put("producto", producto);
            jsonData.put("bodega", bodega);
            jsonData.put("ubicacion", ubicacion);
            jsonData.put("cantidad", cantidad);

            // Configurar la conexión HTTP
            URL url = new URL(api);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Enviar los datos en el cuerpo de la solicitud
            OutputStream os = conn.getOutputStream();
            byte[] input = jsonData.toString().getBytes("utf-8");
            os.write(input, 0, input.length);

            // Obtener la respuesta del servidor
            int statusCode = conn.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) { // 200 OK
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Puedes procesar la respuesta si es necesario
                System.out.println("Respuesta del servidor: " + response.toString());
                return true;
            } else {
                System.out.println("Error al guardar el producto. Código de estado: " + statusCode);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
