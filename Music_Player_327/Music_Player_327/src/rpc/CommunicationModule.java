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
public class CommunicationModule {
    
    private byte[] buf = new byte[15000];
    private static final int PORT = 3000;
    private static InetAddress address; 
    
    private final DatagramSocket socket = new DatagramSocket();
    

    
    public CommunicationModule() throws SocketException {
        try {
            System.out.println("Getting Inet Address");
            address = InetAddress.getLocalHost();

        } catch (UnknownHostException ex) {

            Logger.getLogger(CommunicationModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Connecting socket");
        socket.connect(address, PORT);
        
    } 
    
    
    
    public String send(JsonObject jsonRequest) throws IOException{
        
        System.out.println("Send is called");
        byte[] buf = jsonRequest.toString().getBytes();
        int length = jsonRequest.toString().getBytes().length;
        
//        byte buf [] = "Hello from client".getBytes();
//        int length = 17;
        
        
        DatagramSocket dataSocket = new DatagramSocket();
        dataSocket.send(new DatagramPacket(buf, length, address, PORT));
        System.out.println("Packet is sent.");
        byte[] resBuf = new byte[15000];
        DatagramPacket reply = new DatagramPacket(resBuf, resBuf.length);
        dataSocket.receive(reply);
       // String receivedMessage = new String(buf, 0, buf.length);
        String receivedMessage = new String(reply.getData()).trim();
        System.out.println(receivedMessage + "Hey");
        
        return receivedMessage;
    }
    
    
//    public static void main(String[] args){
//        
//        System.out.println("Main started");
//        
//        JsonObject jsonRequest = new JsonObject();
//        JsonObject jsonParam = new JsonObject();
//        System.out.println("jsonObject is made");
//        
//        jsonRequest.addProperty("remoteMethod", "a remote method");
//        jsonRequest.addProperty("objectName", "Hello");
//        
//        jsonParam.addProperty("song", "param 1");
//        jsonParam.addProperty("fragment", "param 2");       
//        jsonRequest.add("Param", jsonParam);
//        
//        System.out.println("jsonRequest is made");
//        
//        System.out.println("DatagramPacket is being made");
//        byte[] buffer = new byte[1024];
//        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
//        
//        System.out.println("DatagramPacket is made");
//        try {
//            System.out.println("Request is being sent to server.");
//            reply = (new ClientModule()).send(jsonRequest);
//            System.out.println("Request is sent to server.");
//        } catch (SocketException ex) {
//            System.out.println(">>>>>>>>> SocketException.");
//            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            System.out.println(">>>>>>>>> IOException.");
//            Logger.getLogger(ClientModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        System.out.println("reply from server: " + Arrays.toString(reply.getData()));
//
//
//        
//    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
