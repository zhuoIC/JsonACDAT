package com.example.usuario.jsonacdat.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by usuario on 23/01/18.
 */

public class Analisis {
    public static String analizarPrimitiva(JSONObject texto) throws JSONException {
        JSONArray jsonContenido;
        JSONObject item;
        String tipo;
        StringBuilder cadena = new StringBuilder();

        tipo = texto.getString("info");
        jsonContenido = new JSONArray(texto.getString("sorteo"));
        cadena.append("Sorteos de la Primitiva:" + "\n");
        for (int i = 0; i < jsonContenido.length(); i++) {
            item = jsonContenido.getJSONObject(i);
            cadena.append(tipo + ":" + item.getString("fecha") + "\n");
            cadena.append(item.getInt("numero1") + ","+item.getInt("numero2") + ","+item.getInt("numero3") + ","+item.getInt("numero4") + ","+item.getInt("numero5") + ","+"\n");
            cadena.append("Reintegro: " + item.getInt("reintegro") + "\n");
            cadena.append("Complementario: " + item.getInt("complementario") + "\n");
            cadena.append("Sorteo: " + item.getInt("sorteo") + "\n");
            cadena.append("\n");
        }

        return cadena.toString();
    }
}
