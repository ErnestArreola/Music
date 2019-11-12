package rpc.Server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private byte[] buf = new byte[15000];
  //  private DatagramPacket request;
    private DatagramPacket reply;
    
    private DatagramPacket packetToBeReceived;
    private DatagramPacket packetToBeSent;
    private boolean currentlyRunning;
    private byte[] messageBuffer =null;
//	private Dispatcher dispatcher;
    
    private ArrayList<String> reqHistory;
    
    
    
    public ServerModule() throws SocketException {
        socket = new DatagramSocket(PORT);
        reqHistory = new ArrayList<>();
        dispatcher = new Dispatcher();
        registerDispatchers();
    }
    
    public void run() throws IOException{
        
        
        System.out.println("Server is listening on PORT: " + PORT);
        
        while(true) {
            byte[] veryBuff = new byte[15000];	
            DatagramPacket request = new DatagramPacket(veryBuff, veryBuff.length);
            socket.receive(request);
            String reqString = (new String(request.getData())).trim();
            System.out.println("Received a Request from " + request.getAddress());
            System.out.println("Request: " + reqString);
            
            if(!reqHistory.contains(reqString)){
                reqHistory.add(reqString);
                
                new Thread (new Runnable() {
                    @Override
                    public void run() {
                        try {
    //                        String ret = dispatcher.dispatch(String.valueOf(request.getData()));
    //                        reply = new DatagramPacket(ret.getBytes(), ret.length(),
    //                                request.getAddress(), request.getPort());
                            String receivedMessage = new String(request.getData());
                            //JsonObject requestAsJsonObject = new JsonParser().parse(receivedMessage).getAsJsonObject();
                            //buf = dispatcher.dispatch(requestAsJsonObject.toString()).getBytes();
                            String res =  dispatcher.dispatch(receivedMessage.trim());
    //                       Dispatcher dis = new Dispatcher();
    //                       String res = dis.dispatch(receivedMessage.trim());
                                        System.out.println("Dispatcher called");
                            byte buff [] = "This is the response from server".getBytes();
                            int length = buff.length;
                            byte[] handle = res.getBytes();
                            reply = new DatagramPacket(handle, handle.length,
                                    request.getAddress(), request.getPort());
                            //String ret = dispatcher.dispatch(String.valueOf(request.getData()));
                            System.out.println("handle >>> " + new String(handle));                                               
                            socket.send(reply);
                            reqHistory.remove(reqString);
                            System.out.println("Reply is sent.");
                        		
                        } catch (IOException ex) {
                            Logger.getLogger(ServerModule.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            }
            
            
            
       } 
    }
    
    
    
    private void registerDispatchers(){
        SongDispatcher songDispatcher = new SongDispatcher();
        dispatcher.registerObject(songDispatcher, "SongServices");
        LoginDispatcher userDispatcher = new LoginDispatcher();
        dispatcher.registerObject(userDispatcher, "LoginServices");
        RegisterDispatcher registerDispatcher = new RegisterDispatcher();
        dispatcher.registerObject(registerDispatcher, "RegisterServices");
        PlaylistDispatcher playlistDispatcher = new PlaylistDispatcher();
        dispatcher.registerObject(playlistDispatcher, "PlaylistServices");
        MusicDispatcher musicDispatcher = new MusicDispatcher();
        dispatcher.registerObject(musicDispatcher, "MusicServices");
    }
    
    
    
    public static void main(String[] args) throws SocketException, IOException {
        
        System.out.println("Server is listening on PORT: " + PORT);
        new ServerModule().run();
        
  
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//        public byte[] startDispatcher(JsonObject request) throws ParseException {
//		System.out.println("Dispatcher called");
//		dispatcher = new Dispatcher();
//		return (dispatcher.dispatch(request.toString())).getBytes();
//	}
//    public byte[] startDispatcher(JsonObject request) throws ParseException {
//            System.out.println("Dispatcher called");
//		dispatcher = new Dispatcher();
//		return (dispatcher.dispatch(request.toString())).getBytes();
//    }
    
}