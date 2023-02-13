/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Api;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author Win10
 */
public class ApiConsume {

    public static void Api() throws IOException, InterruptedException, SQLException {

        String apiUrl = "https://jsonplaceholder.typicode.com/todos/";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseString = response.body();

        String jsonString = response.body();
        System.out.println(jsonString);
        System.out.println("------");

        Gson gson = new Gson();
        Api[] obj = gson.fromJson(jsonString, Api[].class);
        for (Api a : obj) {
            System.out.println(a.getId());
        }
        boolean t = ApiDbOperation.doInsert(obj);

    }

}
