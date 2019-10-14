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
public class CommunicationModule {
    static final int FRAGMENT_SIZE = 15000;
    private byte[] buf = new byte[1024];
    private static int PORT;
    private static InetAddress address; 
    
    private DatagramSocket socket = null;
    
    
    public void connect(int port){
    try {
        
    this.address= InetAddress.getByName("localhost");
    
    this.socket = new DatagramSocket();
    
    this.PORT = port;
   
    
    
    
    
    } catch (UnknownHostException e){
        e.printStackTrace();
}
    
    catch(IOException e){
    e.printStackTrace();
    }
    }
    

    
    public CommunicationModule() throws SocketException {
//        try {
//            address = InetAddress.getByName("localhost");
//
//        } catch (UnknownHostException ex) {
//
//            Logger.getLogger(CommunicationModule.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        socket.connect(address, PORT);
    }
    
    
    
    public String send(String request) throws IOException{
        
        String response = "";
        try {
        buf = new byte[FRAGMENT_SIZE];
        byte[] buffer = new byte[FRAGMENT_SIZE];
        buffer = request.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(buffer,buffer.length, this.address, this.PORT);
        System.out.println("Client sending request packet to server..");
        int length = request.toString().getBytes().length;
        socket.send(requestPacket);
        
        
        byte[] responseData = new byte[FRAGMENT_SIZE];
        DatagramPacket reply = new DatagramPacket(responseData, responseData.length);
        socket.receive(reply);
        System.out.println("reply received" + reply);
        response = new String(reply.getData());
        socket.close();
        
        }catch (IOException e){
        e.printStackTrace();
        }
        return response;
        }
    }
    
    

    
