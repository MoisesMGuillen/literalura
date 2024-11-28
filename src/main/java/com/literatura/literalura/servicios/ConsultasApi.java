package com.literatura.literalura.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultasApi {
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String urlBase = "https://gutendex.com/books/?search=";
    //Para mapear el Json
    ObjectMapper mapper = new ObjectMapper();

    public String buscaLibro(String busqueda) {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( urlBase + busqueda ))
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return "libro no encontrado";
        }
        String respuesta = response.body();
        try {
            JsonNode jsonRespuesta = mapper.readTree( respuesta ).get("results"); //obtener resultados
            return jsonRespuesta.get(0).toString(); //retorna el primer resultado
        } catch(NullPointerException | JsonProcessingException e){
            return "libro no encontrado";
        }
    }
}
