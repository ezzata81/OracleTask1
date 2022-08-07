package com.ezzat;

import com.sun.net.httpserver.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class ResourceServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(ResourceServer::handleRequest);
        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
//        URI requestURI = exchange.getRequestURI();
//        printRequestInfo(exchange);
    	
        Headers requestHeaders = exchange.getRequestHeaders();
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
        OutputStream os = exchange.getResponseBody();
        //file reader
        int i = 0;
        String x="";
        FileReader fr = new FileReader("D:/file.txt");  
        while ((i = fr.read()) != -1) { 
            x=x+((char)i);
        	//System.out.print((char) i);
            }  
        fr.close();  
        //System.out.println(x);
    
        //end 
       // String access_code=exchange.getResponseBody().toString(); // Comment to retrieve Last OS written By doPost1();
        //String access_code="GBAyfVL7YWtP6gudLIjbRZV_N0dW4f3xETiIxqtokEAZ6FAsBtgyIq0MpU1uQ7J08xOTO2zwP0OuO3pMVAUTid";
        String access_code_res_ser="GBAyfVL7YWtP6gudLIjbRZV_N0dW4f3xETiIxqtokEAZ6FAsBtgyIq0MpU1uQ7J08xOTO2zwP0OuO3pMVAUTid";
        String response = "";
        if (requestMethod.equals("GET")) {
        	//update for Get Resource server 
        	 if (requestHeaders.containsKey("Authorization")) {
                
                 //update 
                 requestHeaders.add("Authorization",x);//adding access code got from Os write to GET Headers  
                 System.out.println(requestHeaders.get("Authorization"));
                 //System.out.println(requestHeaders.get("Authorization",os));
                 //end
             } else {
                 System.out.println("there is no Test in the header");
             }
        	//end
        	 if(access_code_res_ser.equals(x))
            response = "{\"message\" : \"Success,Access is available After Resource Server Approval  \"}";
        	 else 
        	response = "{\"message\" : \"Sorry , Access Code Not Matched \"}";
            exchange.sendResponseHeaders(401, response.getBytes().length);
        } else if (requestMethod.equals("POST")) {
            if (requestHeaders.containsKey("Authorization")) {
                System.out.println(requestHeaders.get("Authorization"));
            } else {
                System.out.println("there is no Test in the header");
            }
            response = "{\"message\" : \"Welcome\"}";
            exchange.sendResponseHeaders(200, response.getBytes().length);
        }
        os.write(response.getBytes());
        os.close();
    }

    private static void printRequestInfo(HttpExchange exchange) {
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
