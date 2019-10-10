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

/**
 *
 * @author sovathana
 */
public class ClientModule {
    
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
        
        
        socket.connect(address, PORT);
    }
    
    
    
    public DatagramPacket send(JsonObject jsonRequest) throws IOException{
        
        byte[] buf = jsonRequest.toString().getBytes();
        int length = jsonRequest.toString().getBytes().length;
        socket.send(new DatagramPacket(buf, length, address, PORT));
        
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
        
        byte[] buffer = new byte[1024];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        
        try {
            reply = new ClientModule().send(jsonRequest);
            System.out.println("Request is sent to server.");
        } catch (SocketException ex) {
            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("reply from server: " + Arrays.toString(reply.getData()));

        System.out.println("Testing");
        
    }
    
}
