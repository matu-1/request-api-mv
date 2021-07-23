package com.codemon.requestapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class Fetch {
    public static String get(String url) throws Exception {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            return getResponse(connection);

        } catch (MalformedURLException e) {
            throw  new Exception("Ups ocurrio un error en la peticion");
        } catch (IOException e) {
           throw new Exception("No hay conexion");
        }
    }

    public static String post(String url, Object body) throws Exception {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            //body
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(new ObjectMapper().writeValueAsString(body));
            writer.flush();

            return getResponse(connection);

        } catch (MalformedURLException e) {
            throw  new Exception("Ups ocurrio un error en la peticion");
        } catch (IOException e) {
            throw new Exception("No hay conexion");
        }
    }

    private static String getResponse(HttpURLConnection connection) throws Exception {
        int status = connection.getResponseCode();
        if(status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return reader.lines().collect(Collectors.joining());
        }else {
            throw new Exception("Ups ocurrio un error");
        }
    }
}
