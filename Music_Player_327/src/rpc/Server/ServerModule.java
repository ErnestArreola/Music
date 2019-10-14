/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.application.Application;
import javafx.stage.Stage;
import rpc.Server.Dispatcher;

/**
 *
 * @author sovathana
 */
public class ServerModule extends Application{
    
    
    public static int PORT;
    private static Dispatcher dispatcher;
    
    private final DatagramSocket socket;
    private byte[] buf = new byte[1024];
    private DatagramPacket request;
    private DatagramPacket reply;
    
    public ServerModule(int portNum, Dispatcher dispatcher) throws SocketException {
        socket = new DatagramSocket(portNum);
        this.dispatcher = dispatcher;
        PORT = portNum;
    }
    

    public void run()  {
        
        
        System.out.println("Server is listening on PORT: " + PORT);
        while(true){
            request = new DatagramPacket(buf, buf.length);
            try {
				socket.receive(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Received a Request from " + request.getAddress());
//            System.out.println("Request: " + (new String(request.getData())).trim());
            
          
                    
                    try {
                    	String receivedMessage = new String(request.getData(), 0, request.getLength());
                    	JsonObject requestAsJsonObject = new JsonParser().parse(receivedMessage).getAsJsonObject();
                    	buf = dispatcher.dispatch(requestAsJsonObject.toString()).getBytes();
                    	InetAddress clientAddress = request.getAddress();
                    	int clientPort = request.getPort();
                    	String msgInBytes = new String(buf);
                    	System.out.println("Retrieved Message" + msgInBytes);
                    	
//                    	String requests = new String(request.getData());
//                        String ret = dispatcher.dispatch(requests.trim());
//                        System.out.println("Server Preparing Packet ");
                        
                        reply = new DatagramPacket(buf, buf.length,
                                clientAddress, request.getPort());
                                                                       
                        socket.send(reply);
                        System.out.println("Reply is sent.");
                        //Thread.interrupt();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ServerModule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                }
                
                
       
       } 
    
    
    
    public static void registerDispatchers() {
//        SongDispatcher songDispatcher = new SongDispatcher();
//        dispatcher.registerObject(songDispatcher, "SongServices");
//        LoginDispatcher userDispatcher = new LoginDispatcher();
//        dispatcher.registerObject(userDispatcher, "LoginServices");
//        RegisterDispatcher registerDispatcher = new RegisterDispatcher();
//        dispatcher.registerObject(registerDispatcher, "RegisterServices");
        PlaylistDispatcher playlistDispatcher = new PlaylistDispatcher();
        dispatcher.registerObject(playlistDispatcher, "PlaylistServices");
    }
    
    
        
    public static void main(String[] args) throws SocketException, IOException {
        ServerModule server = new ServerModule(3000, dispatcher);
        registerDispatchers();
        server.run();

           
        
    }
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
