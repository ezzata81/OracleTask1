package com.ezzat;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class AuthorizationServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(AuthorizationServer::handleRequest);
        //context.setHandler(AuthorizationServer::printRequestInfo);
        server.start();
        //update
        //context.setHandler(AuthorizationServer::printRequestInfo);
        
    }
    
    
    private static void handleRequest(HttpExchange exchange) throws IOException {
//        URI requestURI = exchange.getRequestURI();
//        printRequestInfo(exchange);
    	String access_code="GBAyfVL7YWtP6gudLIjbRZV_N0dW4f3xETiIxqtokEAZ6FAsBtgyIq0MpU1uQ7J08xOTO2zwP0OuO3pMVAUTid";
        Headers requestHeaders = exchange.getRequestHeaders();
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
        OutputStream os = exchange.getResponseBody();
        String response = "";
        if (requestMethod.equals("GET")) {
            response = "{\"message\" : \"unauthorized please login\"}";
            exchange.sendResponseHeaders(401, response.getBytes().length);
        } else if (requestMethod.equals("POST")) {
            if (requestHeaders.containsKey("Authorization")) {
                System.out.println(requestHeaders.get("Authorization"));
            } else {
                System.out.println("there is no Test in the header");
            }
            response = "{\"Access Code\" : "+access_code+"}";
            response = access_code;
            exchange.sendResponseHeaders(200, response.getBytes().length);
        }
        os.write(response.getBytes());
        os.close();
    }

    public static void printRequestInfo(HttpExchange exchange) {
        System.out.println("-- headers --");
        Headers requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);

        System.out.println("-- principle --");
        HttpPrincipal principal = exchange.getPrincipal();
        System.out.println(principal);

        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);

        System.out.println("-- query --");
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        System.out.println(query);
    }
}
