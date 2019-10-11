/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author sovathana
 */
public class ClientModule extends Application{
    
    private byte[] buf = new byte[1024];
    private static final int PORT = 3000;
    private static InetAddress address; 
    
    private final DatagramSocket socket = new DatagramSocket();
    

    
    public ClientModule() throws SocketException {
        try {
            address = InetAddress.getByName("localhost");

        } catch (UnknownHostException ex) {

            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Connecting socket");
        socket.connect(address, PORT);
    }
    
    
    
    public DatagramPacket send(JsonObject jsonRequest) throws IOException{
        
        byte[] buf = jsonRequest.toString().getBytes();
        int length = jsonRequest.toString().getBytes().length;
        socket.send(new DatagramPacket(buf, length, address, PORT));
        System.out.println("Packet is sent.");
        
        DatagramPacket reply = new DatagramPacket(buf, buf.length);
        socket.receive(reply);
        return reply;
    }
    
    
    public static void main(String[] args){
        
        
        JsonObject jsonRequest = new JsonObject();
        JsonObject jsonParam = new JsonObject();
        
        jsonRequest.addProperty("remoteMethod", "a remote method");
        jsonRequest.addProperty("objectName", "Hello");
        
        jsonParam.addProperty("song", "param 1");
        jsonParam.addProperty("fragment", "param 2");       
        jsonRequest.add("Param", jsonParam);
        
        System.out.println("jsonRequest is made");
        
        byte[] buffer = new byte[1024];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        
        try {
            reply = (new ClientModule()).send(jsonRequest);
            System.out.println("Request is sent to server.");
        } catch (SocketException ex) {
            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        System.out.println("reply from server: " + (new String (reply.getData())).trim());

        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
