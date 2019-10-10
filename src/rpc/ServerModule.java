/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author sovathana
 */
public class ServerModule extends Application{
    
    
    public static final int PORT = 3000;
    private static Dispatcher dispatcher;
    
    private final DatagramSocket socket;
    private byte[] buf = new byte[1024];
    private DatagramPacket request;
    private DatagramPacket reply;
    
    public ServerModule() throws SocketException {
        socket = new DatagramSocket(PORT);
        
        dispatcher = new Dispatcher();
    }
    
    public void run() throws IOException{
        
        
        System.out.println("Server is listening on PORT: " + PORT);
        while(true){
            request = new DatagramPacket(buf, buf.length);
            socket.receive(request);
            System.out.println("Received a Request from " + request.getAddress());
            System.out.println("Request: " + request.getData());
            
            
            new Thread (new Runnable(){
                @Override
                public void run() {
                    
                    try {
                        String ret = dispatcher.dispatch(String.valueOf(request.getData()));
                        
                        reply = new DatagramPacket(ret.getBytes(), ret.length(),
                                request.getAddress(), request.getPort());
                        
                        socket.send(reply);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ServerModule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                }
                
                
            }).start();
       } 
    }
    
    
    private void registerDispatchers(){
        
    }
    
    
    
    public static void main(String[] args) throws SocketException, IOException {
        
        System.out.println("Main: Server is listening on PORT: ");
        new ServerModule().run();
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
