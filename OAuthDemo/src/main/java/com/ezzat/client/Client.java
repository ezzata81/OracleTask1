package com.ezzat.client;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.concurrent.Flow;


import javax.swing.JOptionPane;

import com.ezzat.AuthorizationServer;
import com.sun.net.httpserver.HttpExchange;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
    	//update 06/08/2022
    	String user_result=ResourceOwner.getUserAuthorization();
    	if(user_result=="No") {
    		JOptionPane.showMessageDialog(null, "Sorry,Resource Owner refused Your Authorization Request ");
    	}
    	else {
    		 if(JOptionPane.showConfirmDialog (null, "For Authorization Server , Do you accept Authorization For this Client  ?","Authorization Server Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    	    		doPost1();// Step 1 &2 in presentation Request for Authorization server to grant Authorization Code or access token  Step 1 and get response from Authorization Server 
    	    		if(JOptionPane.showConfirmDialog (null, "For Resource Server , Do you accept Grant access  For this Client  ?","Resource Server Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        	    		doGet2(); //Step 3 & 4 in presentation for resource server Grant resources 
     	        }
        		 else doGet1();
     	        
     		}
    	   
 	        
    		 else doGet1();
 	        
 		}
    		//doPost1();//Request for Authorization server to grant Authorization Code or access token  Step 1 and get response from Authorization Server 
    		//doGet2();
    		
    	}
    	
    	//end update 
    	
    	
        // send Get
        //doGet();

        // send POST
        //doPost();
    

    private static void doPost1() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .POST(new HttpRequest.BodyPublisher() {
                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {

                    }
                })
                //.headers("Authorization", "Bearer 7ot el token hena el enta gebto men el OAuth2.0")
                .headers("Authorization", "Send The Authorization Access Token OAuth2.0")
                .build();
   

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        
        //update 
       // String requestMethod = exchange.getRequestMethod();
        //AuthorizationServer.printRequestInfo(HttpExchange );

        //end update
        System.out.println(response.body());
        //Write output to file
        String str=response.body();
        try {

            // attach a file to FileWriter
            FileWriter fw
                = new FileWriter("D:/file.txt");

            // read each character from string and write
            // into FileWriter
            for (int i = 0; i < str.length(); i++)
                fw.write(str.charAt(i));

            System.out.println("Successfully written on text file ");

            // close the file
            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        //end 
        
    }

    private static void doGet1() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
    
    //Update 06/08  for adding resource server 
    private static void doPost2() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090"))
                .POST(new HttpRequest.BodyPublisher() {
                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {

                    }
                })
                //.headers("Authorization", "Bearer 7ot el token hena el enta gebto men el OAuth2.0")
                .headers("Authorization", "Send The Authorization Access Token OAuth2.0")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static void doGet2() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090"))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
    


}